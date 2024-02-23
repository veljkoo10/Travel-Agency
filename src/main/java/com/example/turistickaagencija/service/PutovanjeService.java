package com.example.turistickaagencija.service;

import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.dto.RezervacijaProfilDTO;
import com.example.turistickaagencija.model.Putovanje;

import java.util.List;

public interface PutovanjeService {
    List<Putovanje> findAll();
    Putovanje findOne(Long id);
    Putovanje save(Putovanje putovanje);
    Putovanje update(Putovanje putovanje);
    Putovanje findOne(String nazivDestinacije, String datumVremePolaska, String datumVremePovratka);

    boolean delete(Long id);

    void updateAkcije(AkcijeDTO akcijeDTO);

    List<Putovanje> findAllWhereFreeSpace();

    List<Putovanje> findAllWhereFreeSpaceSpecial();

    List<RezervacijaProfilDTO> findAllReservationsForUser(Long idKorisnika);

    void dodajSlobodnaMesta(Long idPutovanja, Integer brojPutnika);
}
