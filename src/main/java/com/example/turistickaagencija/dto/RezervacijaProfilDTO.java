package com.example.turistickaagencija.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RezervacijaProfilDTO {
    Long id;
    String nazivDestinacije;
    String datumPolaska;
    String datumPovratka;
    int brojMesta;
    double cenaAranzmana;

    public RezervacijaProfilDTO(Long id, String nazivDestinacije, String datumPolaska, String datumPovratka, int brojMesta, double cenaAranzmana) {
        this.id = id;
        this.nazivDestinacije = nazivDestinacije;
        this.datumPolaska = datumPolaska;
        this.datumPovratka = datumPovratka;
        this.brojMesta = brojMesta;
        this.cenaAranzmana = cenaAranzmana;
    }

    public RezervacijaProfilDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivDestinacije() {
        return nazivDestinacije;
    }

    public void setNazivDestinacije(String nazivDestinacije) {
        this.nazivDestinacije = nazivDestinacije;
    }

    public String getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(String datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public String getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(String datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public int getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(int brojMesta) {
        this.brojMesta = brojMesta;
    }

    public double getCenaAranzmana() {
        return cenaAranzmana;
    }

    public void setCenaAranzmana(double cenaAranzmana) {
        this.cenaAranzmana = cenaAranzmana;
    }

    public boolean mogucnostOtkazivanja(String datumPolaska) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime polazakDate = LocalDateTime.parse(datumPolaska, formatter);
        LocalDateTime danas = LocalDateTime.now();
        if(danas.plusDays(2l).isAfter(polazakDate)) return false;
        return true;
    }
}
