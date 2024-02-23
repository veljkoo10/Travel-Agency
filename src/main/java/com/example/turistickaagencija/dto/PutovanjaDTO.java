package com.example.turistickaagencija.dto;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class PutovanjaDTO {
    Long id;
    String prevoznoSredstvo;
    String smestajnaJedinica;
    String nazivDestinacije;
    String datumVremePolaska;
    String datumVremePovratka;
    Integer brojNocenja;
    Double cenaAranzmana;

    Integer ukupanBrojMesta;
    Integer brojSlobodnihMesta;
    MultipartFile slikaLokacije;
    String nazivKategorije;
    public PutovanjaDTO() {
    }

    public PutovanjaDTO(String prevoznoSredstvo, String smestajnaJedinica, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer brojNocenja, Double cenaAranzmana, Integer ukupanBrojMesta, Integer brojSlobodnihMesta, MultipartFile slikaLokacije, String nazivKategorije) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
        this.slikaLokacije = slikaLokacije;
        this.nazivKategorije = nazivKategorije;
    }

    public PutovanjaDTO(Long id, String prevoznoSredstvo, String smestajnaJedinica, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer brojNocenja, Double cenaAranzmana, Integer ukupanBrojMesta, Integer brojSlobodnihMesta, MultipartFile slikaLokacije, String nazivKategorije) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.smestajnaJedinica = smestajnaJedinica;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojNocenja = brojNocenja;
        this.cenaAranzmana = cenaAranzmana;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.brojSlobodnihMesta = brojSlobodnihMesta;
        this.slikaLokacije = slikaLokacije;
        this.nazivKategorije = nazivKategorije;
    }

    public String getPrevoznoSredstvo() {
        return prevoznoSredstvo;
    }

    public void setPrevoznoSredstvo(String prevoznoSredstvo) {
        this.prevoznoSredstvo = prevoznoSredstvo;
    }

    public String getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    public void setSmestajnaJedinica(String smestajnaJedinica) {
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public String getNazivDestinacije() {
        return nazivDestinacije;
    }

    public void setNazivDestinacije(String nazivDestinacije) {
        this.nazivDestinacije = nazivDestinacije;
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

    public Integer getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(Integer brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public Double getCenaAranzmana() {
        return cenaAranzmana;
    }

    public void setCenaAranzmana(Double cenaAranzmana) {
        this.cenaAranzmana = cenaAranzmana;
    }

    public Integer getUkupanBrojMesta() {
        return ukupanBrojMesta;
    }

    public void setUkupanBrojMesta(Integer ukupanBrojMesta) {
        this.ukupanBrojMesta = ukupanBrojMesta;
    }

    public Integer getBrojSlobodnihMesta() {
        return brojSlobodnihMesta;
    }

    public void setBrojSlobodnihMesta(Integer brojSlobodnihMesta) {
        this.brojSlobodnihMesta = brojSlobodnihMesta;
    }

    public MultipartFile getSlikaLokacije() {
        return slikaLokacije;
    }

    public void setSlikaLokacije(MultipartFile slikaLokacije) {
        this.slikaLokacije = slikaLokacije;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}