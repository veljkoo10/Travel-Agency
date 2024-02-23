package com.example.turistickaagencija.controller;

import com.example.turistickaagencija.dto.KorisnikDTO;
import com.example.turistickaagencija.dto.RezervacijaDTO;
import com.example.turistickaagencija.dto.RezervacijaProfilDTO;
import com.example.turistickaagencija.enums.Uloga;
import com.example.turistickaagencija.model.Korisnik;
import com.example.turistickaagencija.model.Rezervacija;
import com.example.turistickaagencija.service.KorisnikService;
import com.example.turistickaagencija.service.PutovanjeService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/korisnici")
public class KorisnikController implements ServletContextAware {
    public static final String KORISNIK_KEY = "korisnik";

    @Autowired
    private ServletContext servletContext;
    private  String bURL;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PutovanjeService putovanjeService;
    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    //TODO: SREDITI DATUM
    @PostMapping(value = "/registracija")
    public void registracija(@RequestParam(required = true) String email, @RequestParam(required = true) String sifra,
                             @RequestParam(required = true) String ime, @RequestParam(required = true) String prezime,
                             @RequestParam(required = true) String datumRodjenja, @RequestParam(required = true) String jmbg,
                             @RequestParam(required = true) String adresa, @RequestParam(required = true) String brojTelefona,
                             HttpSession session, HttpServletResponse response) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Korisnik korisnik = new Korisnik(ime,prezime,email,sifra,datumRodjenja,jmbg,adresa,brojTelefona,LocalDateTime.now().toString(),Uloga.KUPAC);
        korisnikService.save(korisnik);

        response.sendRedirect(bURL + "korisnici/login");
    }


    @GetMapping(value = "/login")
    public ModelAndView getLogin(HttpSession session, HttpServletResponse response) throws IOException {

        ModelAndView rezultat = new ModelAndView("prijava");

        return rezultat;
    }

    @GetMapping(value = "/registracija")
    public ModelAndView getRegistracija(HttpSession session, HttpServletResponse response) throws IOException {

        ModelAndView rezultat = new ModelAndView("registracija");

        return rezultat;
    }

    @GetMapping(value = "/logout")
    public ModelAndView Logout(HttpSession session, HttpServletResponse response) throws IOException {

        session.removeAttribute("korisnik");
        ModelAndView rezultat = new ModelAndView("prijava");

        return rezultat;
    }

    @GetMapping(value = "/profil")
    public ModelAndView Profil(HttpSession session, HttpServletResponse response) throws IOException {

        Long idKorisnika = (Long)session.getAttribute("korisnik");
        Korisnik korisnik = korisnikService.findOne(idKorisnika);
        List<RezervacijaProfilDTO> rezervacije = putovanjeService.findAllReservationsForUser(idKorisnika);
        ModelAndView rezultat = new ModelAndView("profil");
        rezultat.addObject("korisnik", korisnik);
        rezultat.addObject("rezervacije", rezervacije);
        return rezultat;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public void postLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String sifra,
                          HttpSession session, HttpServletResponse response) throws IOException {

        Korisnik korisnik = korisnikService.findOne(email, sifra);
        String greska = "";
        if (korisnik == null)
            greska = "neispravni kredencijali";
        else if (session.getAttribute(KORISNIK_KEY) != null && session.getAttribute(KORISNIK_KEY) != "")
            greska = "korisnik je već prijavljen na sistem morate se prethodno odjaviti<br/>";


        if (!greska.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out;
            out = response.getWriter();

            StringBuilder retVal = new StringBuilder();
            retVal.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "	<meta charset=\"UTF-8\">\r\n"
                    + "	<base href=\"/TuristickaAgencija/\">	\r\n" + "	<title>Prijava korisnika</title>\r\n"
                    + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n"
                    + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"
                    + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/Globalni.css\"/>\r\n"
                    +"    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ModernijiStil.css\"/>\r\n"
                    +"    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/LoginRegistracija.css\"/>\r\n"
                    + "</head>\r\n" + "<body>\r\n" + "	<ul>\r\n"
                    + "		<li><a href=\"korisnici/registracija\">Registruj se</a></li>\r\n" + "	</ul>\r\n");
            if (!greska.equals(""))
                retVal.append("	<div>" + greska + "</div>\r\n");
            retVal.append("	<a href=\"korisnici/login\">Povratak</a>\r\n" + "	<br/>\r\n" + "</body>\r\n" + "</html>");

            out.write(retVal.toString());
            return;
        }

        session.setAttribute("korisnik", korisnik.getId());
        response.sendRedirect(bURL + "putovanja");
    }

    @PostMapping(value="/edit")
    public void create(@ModelAttribute KorisnikDTO korisnikDTO, HttpServletResponse response, HttpSession session) throws IOException, IOException {
        Korisnik korisnik = new Korisnik();
        korisnik.setId((Long)session.getAttribute("korisnik"));
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setEmail(korisnikDTO.getEmail());
        korisnik.setJmbg(korisnikDTO.getJmbg());
        korisnik.setAdresa(korisnikDTO.getAdresa());
        korisnik.setBrTelefona(korisnikDTO.getBrojTelefona());
        korisnik.setDatumRodjenja(korisnikDTO.getDatumRodjenja());
        korisnikService.update(korisnik);
        response.sendRedirect(bURL+ "putovanja");

    }

    @GetMapping(value = "/izmenaLozinke")
    public ModelAndView getIzmenaLozinke(HttpSession session, HttpServletResponse response) throws IOException {

        ModelAndView rezultat = new ModelAndView("izmenaLozinke");
        return rezultat;
    }

    @PostMapping(value = "/izmenaLozinke")
    public void izmenaLozinke(@RequestParam(required = true) String sifra, @RequestParam(required = true) String potvrdaSifre,
                              HttpSession session, HttpServletResponse response) throws IOException {
        if(sifra.isEmpty() || potvrdaSifre.isEmpty())
            response.sendRedirect(bURL + "korisnici/profil");
        if(sifra.isBlank() || potvrdaSifre.isBlank())
            response.sendRedirect(bURL + "korisnici/profil");
        if(sifra.equals(potvrdaSifre))
            korisnikService.updatePassword(sifra, (Long)session.getAttribute("korisnik"));
        response.sendRedirect(bURL + "korisnici/profil");
    }
}