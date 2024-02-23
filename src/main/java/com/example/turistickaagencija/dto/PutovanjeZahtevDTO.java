package com.example.turistickaagencija.dto;

public class PutovanjeZahtevDTO {
    String prevoznoSredstvo;
    String nazivDestinacije;
    String datumVremePolaska;
    String datumVremePovratka;
    Integer ukupanBrojMesta;

    Double cenaAranzmana;

    Long idKorisnika;

    Long idPutovanja;
    String smestajnaJedinica;
    Integer brojNocenja;



    public PutovanjeZahtevDTO(String prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer ukupanBrojMesta) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.ukupanBrojMesta = ukupanBrojMesta;
    }

    public PutovanjeZahtevDTO(String prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer ukupanBrojMesta, Double cenaAranzmana, Long idKorisnika, Long idPutovanja) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
    }

    public PutovanjeZahtevDTO(String prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer ukupanBrojMesta, Double cenaAranzmana, Long idKorisnika, Long idPutovanja, String smestajnaJedinica) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public PutovanjeZahtevDTO(String prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, Integer ukupanBrojMesta, Double cenaAranzmana, Long idKorisnika, Long idPutovanja, String smestajnaJedinica, Integer brojNocenja) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.ukupanBrojMesta = ukupanBrojMesta;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
        this.smestajnaJedinica = smestajnaJedinica;
        this.brojNocenja = brojNocenja;
    }

    public PutovanjeZahtevDTO() {
    }

    public String getPrevoznoSredstvo() {
        return prevoznoSredstvo;
    }

    public void setPrevoznoSredstvo(String prevoznoSredstvo) {
        this.prevoznoSredstvo = prevoznoSredstvo;
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

    public Integer getUkupanBrojMesta() {
        return ukupanBrojMesta;
    }

    public void setUkupanBrojMesta(Integer ukupanBrojMesta) {
        this.ukupanBrojMesta = ukupanBrojMesta;
    }

    public Double getCenaAranzmana() {
        return cenaAranzmana;
    }

    public void setCenaAranzmana(Double cenaAranzmana) {
        this.cenaAranzmana = cenaAranzmana;
    }

    public Long getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Long idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Long getIdPutovanja() {
        return idPutovanja;
    }

    public void setIdPutovanja(Long idPutovanja) {
        this.idPutovanja = idPutovanja;
    }

    public String getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    public void setSmestajnaJedinica(String smestajnaJedinica) {
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public Integer getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(Integer brojNocenja) {
        this.brojNocenja = brojNocenja;
    }
}
