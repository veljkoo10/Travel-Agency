package com.example.turistickaagencija.controller;

import com.example.turistickaagencija.dto.OdbijanjeZahtevaDTO;
import com.example.turistickaagencija.dto.PutovanjeZahtevDTO;
import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;
import com.example.turistickaagencija.enums.Uloga;
import com.example.turistickaagencija.model.Korisnik;
import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.model.PutovanjeZahtev;
import com.example.turistickaagencija.model.Rezervacija;
import com.example.turistickaagencija.service.KorisnikService;
import com.example.turistickaagencija.service.PutovanjeService;
import com.example.turistickaagencija.service.PutovanjeZahtevService;
import com.example.turistickaagencija.service.RezervacijaService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/putovanja/zahtev")
public class PutovanjeZahtevController {
    @Autowired
    private ServletContext servletContext;
    private  String bURL;

    @Autowired
    private PutovanjeZahtevService putovanjeZahtevService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PutovanjeService putovanjeService;
    @Autowired
    private RezervacijaService rezervacijaService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @PostMapping(value="/add")
    public void create(@ModelAttribute PutovanjeZahtevDTO putovanjaZahtevDTO, HttpServletResponse response, HttpSession session) throws IOException, IOException {
        PutovanjeZahtev putovanjeZahtev = new PutovanjeZahtev();
        putovanjeZahtev.setBrojPutnika(putovanjaZahtevDTO.getUkupanBrojMesta());
        putovanjeZahtev.setDatumVremePolaska(putovanjaZahtevDTO.getDatumVremePolaska());
        putovanjeZahtev.setDatumVremePovratka(putovanjaZahtevDTO.getDatumVremePovratka());
        putovanjeZahtev.setPrevoznoSredstvo(PrevoznoSredstvo.valueOf(putovanjaZahtevDTO.getPrevoznoSredstvo()));
        putovanjeZahtev.setNazivDestinacije(putovanjaZahtevDTO.getNazivDestinacije());
        putovanjeZahtev.setIdKorisnika((Long)session.getAttribute("korisnik"));

        putovanjeZahtevService.save(putovanjeZahtev);
        response.sendRedirect(bURL+"putovanja");
    }

    @GetMapping()
    public ModelAndView putovanjeZahtev(HttpSession session, HttpServletResponse response) throws IOException {
        Korisnik korisnik = korisnikService.findOne((Long)session.getAttribute("korisnik"));
        ModelAndView rezultat = new ModelAndView("putovanjeZahtev");
        rezultat.addObject("korisnik", korisnik);

        return rezultat;
    }

    @GetMapping("/all")
    public ModelAndView sviZahtevi(HttpSession session, HttpServletResponse response) throws IOException {
        List<PutovanjeZahtev> putovanja = putovanjeZahtevService.findAll();
        ModelAndView rezultat = new ModelAndView("zahteviPutovanjaMenadzer");
        rezultat.addObject("putovanja", putovanja);

        return rezultat;
    }

    @GetMapping("/kreiraj/{id}")
    public ModelAndView getKreiranjePutovanja(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(Long.parseLong(id));
        ModelAndView rezultat = new ModelAndView("putovanjeZahtevMenadzer");
        rezultat.addObject("putovanje", putovanjeZahtev);

        return rezultat;
    }

    @GetMapping("/odbij/{id}")
    public ModelAndView odbijZahtev(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(Long.parseLong(id));
        ModelAndView rezultat = new ModelAndView("odbijZahtev");
        rezultat.addObject("putovanje", putovanjeZahtev);

        return rezultat;
    }

    @GetMapping("/kupac")
    public ModelAndView getAll(HttpSession session, HttpServletResponse response) throws IOException {
        Long id = (Long)session.getAttribute("korisnik");
        List<PutovanjeZahtev> putovanja = putovanjeZahtevService.findAllForKupac(id);
        ModelAndView rezultat = new ModelAndView("revidiraniZahteviPutovanja");
        rezultat.addObject("putovanja", putovanja);

        return rezultat;
    }

    @GetMapping("/rezervisi/{id}")
    public void rezervisi(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        Long idKorisnika = (Long)session.getAttribute("korisnik");
        PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(Long.parseLong(id));
        Putovanje putovanje = new Putovanje();
        putovanje.setPrevoznoSredstvo(putovanjeZahtev.getPrevoznoSredstvo());
        putovanje.setSmestajnaJedinica(putovanjeZahtev.getSmestajnaJedinica());
        putovanje.setSlikaLokacije(null);
        putovanje.setDatumVremePolaska(putovanjeZahtev.getDatumVremePolaska());
        putovanje.setDatumVremePovratka(putovanjeZahtev.getDatumVremePovratka());
        putovanje.setBrojNocenja(putovanje.getBrojNocenja());
        putovanje.setCenaAranzmana(putovanjeZahtev.getCenaAranzmana());
        putovanje.setUkupanBrojMesta(putovanjeZahtev.getBrojPutnika());
        putovanje.setBrojSlobodnihMesta(0);
        putovanje.setNazivKategorije("");
        putovanje.setNazivDestinacije(putovanjeZahtev.getNazivDestinacije());
        putovanjeService.save(putovanje);
        Putovanje kreiranoPutovanje = putovanjeService.findOne(putovanje.getNazivDestinacije(), putovanje.getDatumVremePolaska(), putovanje.getDatumVremePovratka());
        Rezervacija rezervacija = new Rezervacija(idKorisnika, kreiranoPutovanje.getId(),
                kreiranoPutovanje.getUkupanBrojMesta(), kreiranoPutovanje.getCenaAranzmana());
        rezervacijaService.save(rezervacija);


        putovanjeZahtevService.rezervisi(Long.valueOf(id));
        response.sendRedirect(bURL+"putovanja");
    }

    @GetMapping("/revidiranje/{id}")
    public ModelAndView getRevidiranje(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(Long.parseLong(id));
        ModelAndView rezultat = new ModelAndView("ponovnoRevidiranje");
        rezultat.addObject("putovanje", putovanjeZahtev);

        return rezultat;
    }

    @PostMapping(value="/revidiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView revidirajPost(@RequestBody OdbijanjeZahtevaDTO zahtev, HttpSession session, HttpServletResponse response) throws IOException {
        PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(zahtev.getId());
        putovanjeZahtevService.revidiraj(putovanjeZahtev, zahtev.getKomentar());
        return putovanjeZahtev(session, response);
    }

    @PostMapping(value = "/odbij", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView odbijZahtevPost(@RequestBody OdbijanjeZahtevaDTO zahtev, HttpSession session, HttpServletResponse response) throws IOException {
        Long ulogovaniId = (Long)session.getAttribute("korisnik");
        if(korisnikService.findOne(ulogovaniId).getUloga().equals(Uloga.MENADZER)) {
            PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(zahtev.getId());
            putovanjeZahtevService.delete(putovanjeZahtev, zahtev.getKomentar(), (Long) session.getAttribute("korisnik"));
            return sviZahtevi(session, response);
        } else {
            PutovanjeZahtev putovanjeZahtev = putovanjeZahtevService.findOne(zahtev.getId());
            putovanjeZahtevService.deleteKupac(putovanjeZahtev, zahtev.getKomentar());
            return putovanjeZahtev(session, response);
        }
    }

    @PostMapping(value="/menadzer/add")
    public void createMenadzer(@ModelAttribute PutovanjeZahtevDTO putovanjaZahtevDTO, HttpServletResponse response, HttpSession session) throws IOException, IOException {
        PutovanjeZahtev putovanjeZahtev = new PutovanjeZahtev();
        putovanjeZahtev.setBrojPutnika(putovanjaZahtevDTO.getUkupanBrojMesta());
        putovanjeZahtev.setDatumVremePolaska(putovanjaZahtevDTO.getDatumVremePolaska());
        putovanjeZahtev.setDatumVremePovratka(putovanjaZahtevDTO.getDatumVremePovratka());
        putovanjeZahtev.setPrevoznoSredstvo(PrevoznoSredstvo.valueOf(putovanjaZahtevDTO.getPrevoznoSredstvo()));
        putovanjeZahtev.setNazivDestinacije(putovanjaZahtevDTO.getNazivDestinacije());
        putovanjeZahtev.setIdKorisnika(putovanjaZahtevDTO.getIdKorisnika());
        putovanjeZahtev.setId(putovanjaZahtevDTO.getIdPutovanja());
        putovanjeZahtev.setCenaAranzmana(putovanjaZahtevDTO.getCenaAranzmana());
        putovanjeZahtev.setSmestajnaJedinica(SmestajnaJedinica.valueOf(putovanjaZahtevDTO.getSmestajnaJedinica()));
        putovanjeZahtev.setBrojNocenja(putovanjaZahtevDTO.getBrojNocenja());
        putovanjeZahtev.setRevidirano(true);
        putovanjeZahtev.setIdMenadzera((Long) session.getAttribute("korisnik"));
        putovanjeZahtevService.saveProposition(putovanjeZahtev);
        response.sendRedirect(bURL+"putovanja");
    }
}
