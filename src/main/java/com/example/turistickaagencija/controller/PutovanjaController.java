package com.example.turistickaagencija.controller;

import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.dto.PutovanjaDTO;
import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;
import com.example.turistickaagencija.enums.Uloga;
import com.example.turistickaagencija.model.Korisnik;
import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.service.FileService;
import com.example.turistickaagencija.service.KorisnikService;
import com.example.turistickaagencija.service.PutovanjeService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/putovanja")
public class PutovanjaController implements ServletContextAware {
    public static final String PUTOVANJE_KEY = "putovanje";
    @Autowired
    FileService storageService;

    @Autowired
    private ServletContext servletContext;
    private  String bURL;

    @Autowired
    private PutovanjeService putovanjeService;

    @Autowired
    private KorisnikService korisnikService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }
    @GetMapping
    public ModelAndView  getPutovanja(HttpSession session, HttpServletResponse response){


        if(session.getAttribute("korisnik") == null || session.getAttribute("korisnik").equals("")){
            List<Putovanje> akcijskaPutovanja = putovanjeService.findAllWhereFreeSpaceSpecial();
            List<Putovanje> putovanja = putovanjeService.findAllWhereFreeSpace();
            List<String> kategorije = new ArrayList<>();
            for (Putovanje putovanje:putovanja) {
                if(!kategorije.contains(putovanje.getNazivKategorije()))
                    kategorije.add(putovanje.getNazivKategorije());
            }
            ModelAndView rezultat = new ModelAndView("pocetna");
            rezultat.addObject("akcijskaPutovanja", akcijskaPutovanja);
            rezultat.addObject("putovanja", putovanja);
            rezultat.addObject("kategorije", kategorije);


            return rezultat;
        }
        else if(korisnikService.findOne((Long)session.getAttribute("korisnik")).getUloga().equals(Uloga.KUPAC)) {
            List<Putovanje> akcijskaPutovanja = putovanjeService.findAllWhereFreeSpaceSpecial();
            List<Putovanje> putovanja = putovanjeService.findAllWhereFreeSpace();
            List<String> kategorije = new ArrayList<>();
            for (Putovanje putovanje:putovanja) {
                if(!kategorije.contains(putovanje.getNazivKategorije()))
                    kategorije.add(putovanje.getNazivKategorije());
            }
            ModelAndView rezultat = new ModelAndView("putovanjaKupac");
            rezultat.addObject("putovanja", putovanja);
            rezultat.addObject("akcijskaPutovanja", akcijskaPutovanja);
            rezultat.addObject("kategorije", kategorije);
            return rezultat;
        } else{
            List<Putovanje> putovanja = putovanjeService.findAll();
            List<String> kategorije = new ArrayList<>();
            for (Putovanje putovanje:putovanja) {
                if(!kategorije.contains(putovanje.getNazivKategorije()))
                    kategorije.add(putovanje.getNazivKategorije());
            }
            ModelAndView rezultat = new ModelAndView("putovanjaMenadzer");
            rezultat.addObject("putovanja", putovanja);
            rezultat.addObject("kategorije", kategorije);

            return rezultat;
        }
    }


    @GetMapping("/dodavanjePutovanja")
    public ModelAndView getDodavanjePutovanja(HttpSession session, HttpServletResponse response){

        Putovanje putovanje = new Putovanje();

        ModelAndView rezultat = new ModelAndView("dodavanjePutovanja");
        rezultat.addObject("putovanje", putovanje);

        return rezultat;
    }

    @PostMapping(value="/add")
    public void create(@ModelAttribute PutovanjaDTO putovanjaDTO, HttpServletResponse response) throws IOException, IOException {
        MultipartFile file = putovanjaDTO.getSlikaLokacije();
        storageService.save(file, putovanjaDTO.getNazivDestinacije() + putovanjaDTO.getDatumVremePolaska().replace(":","_"));
        Putovanje putovanje = new Putovanje(PrevoznoSredstvo.valueOf(putovanjaDTO.getPrevoznoSredstvo()), SmestajnaJedinica.valueOf(putovanjaDTO.getSmestajnaJedinica()), putovanjaDTO.getNazivDestinacije(), "images/" + putovanjaDTO.getNazivDestinacije() + putovanjaDTO.getDatumVremePolaska().replace(":", "_") + "/"+putovanjaDTO.getSlikaLokacije().getOriginalFilename(), putovanjaDTO.getDatumVremePolaska(), putovanjaDTO.getDatumVremePovratka(), putovanjaDTO.getBrojNocenja(), putovanjaDTO.getCenaAranzmana(), putovanjaDTO.getUkupanBrojMesta(), putovanjaDTO.getBrojSlobodnihMesta(), putovanjaDTO.getNazivKategorije());
        putovanjeService.save(putovanje);
        response.sendRedirect(bURL+"putovanja");

    }

    @GetMapping("/izmena/{id}")
    public ModelAndView izmenaPutovanjaStranica(@PathVariable String id, HttpSession session, HttpServletResponse response) {
        ModelAndView rezultat = new ModelAndView("azuriranjePutovanja");
        rezultat.addObject("putovanje", putovanjeService.findOne(Long.parseLong(id)));
        return rezultat;
    }

    @GetMapping("/akcije/{id}")
    public ModelAndView izmenaAkcijaStranica(@PathVariable String id, HttpSession session, HttpServletResponse response) {
        ModelAndView rezultat = new ModelAndView("putovanjeAkcije");
        rezultat.addObject("putovanje", putovanjeService.findOne(Long.parseLong(id)));
        return rezultat;
    }

    @GetMapping("/delete/{id}")
    public void brisanjePutovanja(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        putovanjeService.delete(Long.parseLong(id));
        response.sendRedirect(bURL+"putovanja");
    }

    @PostMapping(value="/update")
    public void update(@ModelAttribute PutovanjaDTO putovanjaDTO, HttpServletResponse response) throws IOException, IOException {
        MultipartFile file = putovanjaDTO.getSlikaLokacije();
        System.out.println(file.getOriginalFilename());
        Putovanje putovanje;
        if(file.getOriginalFilename() != null && !file.getOriginalFilename().isBlank() && !file.getOriginalFilename().isEmpty()) {
            storageService.save(file, putovanjaDTO.getNazivDestinacije() + putovanjaDTO.getDatumVremePolaska().replace(":", "_"));
            putovanje = new Putovanje(PrevoznoSredstvo.valueOf(putovanjaDTO.getPrevoznoSredstvo()), SmestajnaJedinica.valueOf(putovanjaDTO.getSmestajnaJedinica()), putovanjaDTO.getNazivDestinacije(), "images/" + putovanjaDTO.getNazivDestinacije() + putovanjaDTO.getDatumVremePolaska().replace(":", "_") + "/" + putovanjaDTO.getSlikaLokacije().getOriginalFilename(), putovanjaDTO.getDatumVremePolaska(), putovanjaDTO.getDatumVremePovratka(), putovanjaDTO.getBrojNocenja(), putovanjaDTO.getCenaAranzmana(), putovanjaDTO.getUkupanBrojMesta(), putovanjaDTO.getBrojSlobodnihMesta(), putovanjaDTO.getNazivKategorije());
        } else
            putovanje = new Putovanje(PrevoznoSredstvo.valueOf(putovanjaDTO.getPrevoznoSredstvo()), SmestajnaJedinica.valueOf(putovanjaDTO.getSmestajnaJedinica()), putovanjaDTO.getNazivDestinacije(),  putovanjaDTO.getDatumVremePolaska(), putovanjaDTO.getDatumVremePovratka(), putovanjaDTO.getBrojNocenja(), putovanjaDTO.getCenaAranzmana(), putovanjaDTO.getUkupanBrojMesta(), putovanjaDTO.getBrojSlobodnihMesta(), putovanjaDTO.getNazivKategorije());

        putovanje.setId(putovanjaDTO.getId());
        putovanjeService.update(putovanje);
        response.sendRedirect(bURL+"putovanja");
    }

    @PostMapping(value="/akcije/update")
    public void updateAkcije(@ModelAttribute AkcijeDTO akcijeDTO, HttpServletResponse response) throws IOException, IOException {
        putovanjeService.updateAkcije(akcijeDTO);
        response.sendRedirect(bURL+"putovanja");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}