package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enums.Uloga;

import java.util.Date;

public class Korisnik {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String datumRodjenja;
    private String jmbg;
    private String adresa;
    private String brTelefona;
    private String datumPrijave;
    private Uloga uloga;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String lozinka, String datumRodjenja, String jmbg, String adresa, String brTelefona, String datumPrijave, Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.brTelefona = brTelefona;
        this.datumPrijave = datumPrijave;
        this.uloga = uloga;
    }

    public Korisnik(Long id, String ime, String prezime, String email, String lozinka, String datumRodjenja, String jmbg, String adresa, String brtelefona, String datumPrijave, Uloga uloga) {
        this.id=id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.brTelefona = brtelefona;
        this.datumPrijave = datumPrijave;
        this.uloga = uloga;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrTelefona() {
        return brTelefona;
    }

    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
    }

    public String getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(String datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
