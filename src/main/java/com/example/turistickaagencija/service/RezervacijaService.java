package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {
    Rezervacija save(Rezervacija rezervacija);

    boolean isReservatedTrip(Long id);

    List<Rezervacija> findAllForUser(Long idKorisnika);

    Rezervacija findOne(Long id);

    void delete(Long aLong);
}
