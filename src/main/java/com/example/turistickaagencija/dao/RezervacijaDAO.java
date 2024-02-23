package com.example.turistickaagencija.dao;

import com.example.turistickaagencija.model.Rezervacija;

import java.util.List;

public interface RezervacijaDAO {
    public int save(Rezervacija rezervacija);
    public Rezervacija findOneByPutovanjeId(Long id);

    List<Rezervacija> findAllForUser(Long idKorisnika);

    Rezervacija findOne(Long id);

    void delete(Long aLong);

}
