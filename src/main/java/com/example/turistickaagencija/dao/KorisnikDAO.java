package com.example.turistickaagencija.dao;

import com.example.turistickaagencija.model.Korisnik;

public interface KorisnikDAO {
    public int save(Korisnik korisnik);
    public Korisnik findOne(String email);
    public Korisnik findOne(Long id);
    public Korisnik findOne(String email, String sifra);
    public int update(Korisnik korisnik);
    public int updatePassword(String sifra, Long id);
}
