package com.example.turistickaagencija.controller;
import com.example.turistickaagencija.model.Komentar;
import com.example.turistickaagencija.service.KomentarService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/komentari")
public class KomentariController {
    @Autowired
    private ServletContext servletContext;
    private  String bURL;

    @Autowired
    private KomentarService komentarService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @GetMapping()
    public ModelAndView getKomentari(HttpSession session, HttpServletResponse response) throws IOException {
        List<Komentar> komentari = komentarService.findAllForUser((Long)session.getAttribute("korisnik"));
        ModelAndView rezultat = new ModelAndView("komentari");
        rezultat.addObject("komentari", komentari);

        return rezultat;
    }
}