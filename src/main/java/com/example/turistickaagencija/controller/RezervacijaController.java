package com.example.turistickaagencija.controller;

import com.example.turistickaagencija.dto.RezervacijaDTO;
import com.example.turistickaagencija.model.Rezervacija;
import com.example.turistickaagencija.service.PutovanjeService;
import com.example.turistickaagencija.service.RezervacijaService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@Controller
@RequestMapping(value = "/rezervacija")
public class RezervacijaController {
    public static final String PUTOVANJE_KEY = "rezervacija";

    @Autowired
    private ServletContext servletContext;
    private  String bURL;

    @Autowired
    private RezervacijaService rezervacijaService;
    @Autowired
    private PutovanjeService putovanjeService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @GetMapping("/{id}")
    public ModelAndView novaRezervacijaStranica(@PathVariable String id, HttpSession session, HttpServletResponse response) {

        ModelAndView rezultat = new ModelAndView("rezervacija");
        rezultat.addObject("putovanje", putovanjeService.findOne(Long.parseLong(id)));
        return rezultat;
    }

    @GetMapping("/otkazi/{id}")
    public void otkaziRezervaciju(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        Rezervacija rezervacija = rezervacijaService.findOne(Long.valueOf(id));
        putovanjeService.dodajSlobodnaMesta(rezervacija.getIdPutovanja(), rezervacija.getBrojPutnika());
        rezervacijaService.delete(Long.valueOf(id));
        ModelAndView rezultat = new ModelAndView("putovanja");
        rezultat.addObject("idPutovanja", id);

        response.sendRedirect(bURL + "korisnici/profil");
    }

    @PostMapping(value="/add")
    public void create(@ModelAttribute RezervacijaDTO rezervacijaDTO, HttpServletResponse response, HttpSession session) throws IOException, IOException {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIdKorisnika((Long)session.getAttribute("korisnik"));
        rezervacija.setIdPutovanja(Long.valueOf(rezervacijaDTO.getIdPutovanja()));
        rezervacija.setBrojPutnika(rezervacijaDTO.getBrojPutnika());
        rezervacijaService.save(rezervacija);
        response.sendRedirect(bURL+ "putovanja");
    }
}
