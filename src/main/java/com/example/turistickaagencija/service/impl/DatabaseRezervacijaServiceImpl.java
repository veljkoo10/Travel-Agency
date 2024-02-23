package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.RezervacijaDAO;
import com.example.turistickaagencija.model.Rezervacija;
import com.example.turistickaagencija.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseRezervacijaServiceImpl implements RezervacijaService {
    @Autowired
    private RezervacijaDAO rezervacijaDAO;
    @Override
    public Rezervacija save(Rezervacija rezervacija) {
        rezervacijaDAO.save(rezervacija);
        return rezervacija;
    }

    @Override
    public boolean isReservatedTrip(Long id) {
        return rezervacijaDAO.findOneByPutovanjeId(id) != null;
    }

    @Override
    public List<Rezervacija> findAllForUser(Long idKorisnika) {
        return rezervacijaDAO.findAllForUser(idKorisnika);
    }

    @Override
    public Rezervacija findOne(Long id) {
        return rezervacijaDAO.findOne(id);
    }

    @Override
    public void delete(Long aLong) {
        rezervacijaDAO.delete(aLong);
    }
}