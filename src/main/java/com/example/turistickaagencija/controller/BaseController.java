package com.example.turistickaagencija.controller;

import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.service.PutovanjeService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class BaseController {
    @Autowired
    PutovanjeService putovanjeService;
    @Autowired
    private ServletContext servletContext;
    private  String bURL;
    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }
    @GetMapping
    public ModelAndView getPutovanja(HttpSession session, HttpServletResponse response) {

        List<Putovanje> akcijskaPutovanja = putovanjeService.findAllWhereFreeSpaceSpecial();
        List<Putovanje> putovanja = putovanjeService.findAllWhereFreeSpace();
        ModelAndView rezultat = new ModelAndView("pocetna");
        rezultat.addObject("akcijskaPutovanja", akcijskaPutovanja);
        rezultat.addObject("putovanja", putovanja);

        return rezultat;
    }
}
