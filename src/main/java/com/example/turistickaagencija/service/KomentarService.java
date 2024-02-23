package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Komentar;

import java.util.List;

public interface KomentarService {
    int save(Komentar komentar);

    List<Komentar> findAllForUser(Long korisnik);

}
