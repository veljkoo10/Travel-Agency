package com.example.turistickaagencija.dao;

import com.example.turistickaagencija.model.Komentar;

import java.util.List;

public interface KomentarDAO {
    int save(Komentar komentar);

    List<Komentar> findAllForUser(Long korisnik);
}
