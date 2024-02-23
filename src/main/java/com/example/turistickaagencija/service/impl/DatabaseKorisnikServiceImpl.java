package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.KorisnikDAO;
import com.example.turistickaagencija.model.Korisnik;
import com.example.turistickaagencija.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseKorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Override
    public Korisnik save(Korisnik korisnik) {
        korisnikDAO.save(korisnik);
        return korisnik;
    }

    @Override
    public Korisnik findOne(String email) {
        return korisnikDAO.findOne(email);
    }

    @Override
    public Korisnik findOne(Long id) {
        return korisnikDAO.findOne(id);
    }

    @Override
    public Korisnik findOne(String email, String sifra) {
        return korisnikDAO.findOne(email, sifra);
    }

    @Override
    public Korisnik update(Korisnik korisnik) {
        korisnikDAO.update(korisnik);
        return korisnik;
    }

    @Override
    public int updatePassword(String sifra, Long id) {
        return korisnikDAO.updatePassword(sifra, id);
    }
}
