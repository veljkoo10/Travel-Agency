package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Putovanje {
    private Long id;
    private PrevoznoSredstvo prevoznoSredstvo;
    private SmestajnaJedinica smestajnaJedinica;
    private String nazivDestinacije;
    private String slikaLokacije;
    private String datumVremePolaska;
    private String datumVremePovratka;
    private int brojNocenja;
    private double cenaAranzmana;
    private int ukupanBrojMesta;
    private int brojSlobodnihMesta;
    private Double procenatPopusta;
    private String datumVazenjaPopusta;
    private String nazivKategorije;

    public Putovanje() {
    }

    public Putovanje(Long id, PrevoznoSredstvo prevoznoSredstvo, SmestajnaJedinica smestajnaJedinica, String nazivDestinacije, String slikaLokacije, String datumVremePolaska, String datumVremePovratka, int brojNocenja, double cenaAranzmana, int ukupanBrojMesta, int brojSlobodnihMesta) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.slikaLokacije = slikaLokacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
    }

    public Putovanje(PrevoznoSredstvo prevoznoSredstvo, SmestajnaJedinica smestajnaJedinica, String nazivDestinacije, String slikaLokacije, String datumVremePolaska, String datumVremePovratka, int brojNocenja, double cenaAranzmana, int ukupanBrojMesta, int brojSlobodnihMesta) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.slikaLokacije = slikaLokacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
    }

    public Putovanje(Long id, PrevoznoSredstvo prevoznoSredstvo, SmestajnaJedinica smestajnaJedinica, String nazivDestinacije, String slikaLokacije, String datumVremePolaska, String datumVremePovratka, int brojNocenja, double cenaAranzmana, int ukupanBrojMesta, int brojSlobodnihMesta, Double procenatPopusta, String datumVazenjaPopusta) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.slikaLokacije = slikaLokacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
        this.procenatPopusta = procenatPopusta;
        this.datumVazenjaPopusta = datumVazenjaPopusta;
    }

    public Putovanje(PrevoznoSredstvo prevoznoSredstvo, SmestajnaJedinica smestajnaJedinica, String nazivDestinacije, String slikaLokacije, String datumVremePolaska, String datumVremePovratka, int brojNocenja, double cenaAranzmana, int ukupanBrojMesta, int brojSlobodnihMesta, String nazivKategorije) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.slikaLokacije = slikaLokacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
        this.nazivKategorije = nazivKategorije;
    }

    public Putovanje(PrevoznoSredstvo prevoznoSredstvo, SmestajnaJedinica smestajnaJedinica, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojNocenja, double cenaAranzmana, int ukupanBrojMesta, int brojSlobodnihMesta, String nazivKategorije) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
        this.nazivKategorije = nazivKategorije;
    }


    public PrevoznoSredstvo getPrevoznoSredstvo() {
        return prevoznoSredstvo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrevoznoSredstvo(PrevoznoSredstvo prevoznoSredstvo) {
        this.prevoznoSredstvo = prevoznoSredstvo;
    }

    public SmestajnaJedinica getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public String getNazivDestinacije() {
        return nazivDestinacije;
    }

    public void setNazivDestinacije(String nazivDestinacije) {
        this.nazivDestinacije = nazivDestinacije;
    }

    public String getSlikaLokacije() {
        return slikaLokacije;
    }

    public void setSlikaLokacije(String slikaLokacije) {
        this.slikaLokacije = slikaLokacije;
    }

    public String getDatumVremePolaska() {
        return datumVremePolaska;
    }

    public void setDatumVremePolaska(String datumVremePolaska) {
        this.datumVremePolaska = datumVremePolaska;
    }

    public String getDatumVremePovratka() {
        return datumVremePovratka;
    }

    public void setDatumVremePovratka(String datumVremePovratka) {
        this.datumVremePovratka = datumVremePovratka;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public double getCenaAranzmana() {
        return cenaAranzmana;
    }

    public void setCenaAranzmana(double cenaAranzmana) {
        this.cenaAranzmana = cenaAranzmana;
    }

    public int getUkupanBrojMesta() {
        return ukupanBrojMesta;
    }

    public void setUkupanBrojMesta(int ukupanBrojMesta) {
        this.ukupanBrojMesta = ukupanBrojMesta;
    }

    public int getBrojSlobodnihMesta() {
        return brojSlobodnihMesta;
    }

    public void setBrojSlobodnihMesta(int brojSlobodnihMesta) {
        this.brojSlobodnihMesta = brojSlobodnihMesta;
    }

    public Double getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(Double procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
    }

    public String getDatumVazenjaPopusta() {
        return datumVazenjaPopusta;
    }

    public void setDatumVazenjaPopusta(String datumVazenjaPopusta) {
        this.datumVazenjaPopusta = datumVazenjaPopusta;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public Double TrenutnaCena() {
        if (procenatPopusta == null || datumVazenjaPopusta == null || cenaAranzmana <= 0) {
            return cenaAranzmana;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime datumVazenja = LocalDateTime.parse(datumVazenjaPopusta, formatter);


        LocalDateTime danas = LocalDateTime.now();
        if (datumVazenja.isBefore(danas)) {
            return cenaAranzmana;
        }

        double popust = cenaAranzmana * (procenatPopusta / 100.0);
        return cenaAranzmana - popust;
    }
}