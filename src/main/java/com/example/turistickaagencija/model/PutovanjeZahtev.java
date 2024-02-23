package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;

public class PutovanjeZahtev {
    private Long id;
    private PrevoznoSredstvo prevoznoSredstvo;
    private String nazivDestinacije;
    private String datumVremePolaska;
    private String datumVremePovratka;
    private int brojPutnika;
    private double cenaAranzmana;
    private Long idKorisnika;
    private Long idMenadzera;
    private SmestajnaJedinica smestajnaJedinica;
    private int brojNocenja;
    private Boolean revidirano;
    private Boolean rezervisano;

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, Long idKorisnika) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.idKorisnika = idKorisnika;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika, SmestajnaJedinica smestajnaJedinica) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika, SmestajnaJedinica smestajnaJedinica, int brojNocenja) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.smestajnaJedinica = smestajnaJedinica;
        this.brojNocenja = brojNocenja;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika, SmestajnaJedinica smestajnaJedinica, int brojNocenja, Boolean revidirano) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.smestajnaJedinica = smestajnaJedinica;
        this.brojNocenja = brojNocenja;
        this.revidirano = revidirano;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika, SmestajnaJedinica smestajnaJedinica, int brojNocenja, Boolean revidirano, Boolean rezervisano) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.smestajnaJedinica = smestajnaJedinica;
        this.brojNocenja = brojNocenja;
        this.revidirano = revidirano;
        this.rezervisano = rezervisano;
    }

    public PutovanjeZahtev(Long id, PrevoznoSredstvo prevoznoSredstvo, String nazivDestinacije, String datumVremePolaska, String datumVremePovratka, int brojPutnika, double cenaAranzmana, Long idKorisnika, Long idMenadzera, SmestajnaJedinica smestajnaJedinica, int brojNocenja, Boolean revidirano, Boolean rezervisano) {
        this.id = id;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.datumVremePolaska = datumVremePolaska;
        this.datumVremePovratka = datumVremePovratka;
        this.brojPutnika = brojPutnika;
        this.cenaAranzmana = cenaAranzmana;
        this.idKorisnika = idKorisnika;
        this.idMenadzera = idMenadzera;
        this.smestajnaJedinica = smestajnaJedinica;
        this.brojNocenja = brojNocenja;
        this.revidirano = revidirano;
        this.rezervisano = rezervisano;
    }

    public PutovanjeZahtev() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrevoznoSredstvo getPrevoznoSredstvo() {
        return prevoznoSredstvo;
    }

    public void setPrevoznoSredstvo(PrevoznoSredstvo prevoznoSredstvo) {
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

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }

    public Long getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Long idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public double getCenaAranzmana() {
        return cenaAranzmana;
    }

    public void setCenaAranzmana(double cenaAranzmana) {
        this.cenaAranzmana = cenaAranzmana;
    }

    public SmestajnaJedinica getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
        this.smestajnaJedinica = smestajnaJedinica;
    }

    public int getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(int brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public Boolean getRevidirano() {
        return revidirano;
    }

    public void setRevidirano(Boolean revidirano) {
        this.revidirano = revidirano;
    }

    public Boolean getRezervisano() {
        return rezervisano;
    }

    public void setRezervisano(Boolean rezervisano) {
        this.rezervisano = rezervisano;
    }

    public Long getIdMenadzera() {
        return idMenadzera;
    }

    public void setIdMenadzera(Long idMenadzera) {
        this.idMenadzera = idMenadzera;
    }
}
