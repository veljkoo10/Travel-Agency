package com.example.turistickaagencija.dao;

import com.example.turistickaagencija.model.PutovanjeZahtev;

import java.util.List;

public interface PutovanjeZahtevDAO {
    public int save(PutovanjeZahtev putovanjeZahtev);

    PutovanjeZahtev findOne(Long id);

    List<PutovanjeZahtev> findAll();

    void saveProposition(PutovanjeZahtev putovanjeZahtev);

    void delete(PutovanjeZahtev putovanje);

    List<PutovanjeZahtev> findAllForKupac(Long id);

    void rezervisi(Long id);

    void revidiranje(PutovanjeZahtev putovanjeZahtev);
}
