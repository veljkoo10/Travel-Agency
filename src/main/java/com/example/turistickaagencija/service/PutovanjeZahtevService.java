package com.example.turistickaagencija.service;

import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.dto.RezervacijaProfilDTO;
import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.model.PutovanjeZahtev;

import java.util.List;

public interface PutovanjeZahtevService {
    PutovanjeZahtev save(PutovanjeZahtev putovanjeZahtev);

    PutovanjeZahtev findOne(Long id);

    List<PutovanjeZahtev> findAll();

    void saveProposition(PutovanjeZahtev putovanjeZahtev);

    void delete(PutovanjeZahtev putovanje, String komentar, Long idMenadzera);

    List<PutovanjeZahtev> findAllForKupac(Long id);

    void rezervisi(Long id);

    void revidiraj(PutovanjeZahtev putovanjeZahtev, String komentar);

    void deleteKupac(PutovanjeZahtev putovanjeZahtev, String komentar);
}
