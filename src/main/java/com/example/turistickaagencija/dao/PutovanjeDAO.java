package com.example.turistickaagencija.dao;

import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.model.Putovanje;

import java.util.List;

public interface PutovanjeDAO {
    public List<Putovanje> findAll();
    public int save(Putovanje putovanje);

    void update(Putovanje putovanje);
    Putovanje findOne(String nazivDestinacije, String datumVremePolaska, String datumVremePovratka);

    Putovanje findOne(Long id);

    boolean delete(Long id);

    void updateAkcije(AkcijeDTO akcijeDTO);

    List<Putovanje> findAllWhereFreeSpace();

    void dodajSlobodnaMesta(Long idPutovanja, Integer brojPutnika);
}
