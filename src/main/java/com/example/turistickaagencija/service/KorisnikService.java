package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Korisnik;

public interface KorisnikService {
	Korisnik save(Korisnik korisnik);
	Korisnik findOne(String email);
	Korisnik findOne(Long id);
	Korisnik findOne(String email, String sifra);
	Korisnik update(Korisnik korisnik);
	int updatePassword(String sifra, Long id);
}
