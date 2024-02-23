package com.example.turistickaagencija.model;

public class Rezervacija {
    private Long id;
    private Long idKorisnika;
    private Long idPutovanja;
    private Integer brojPutnika;

    private Double cena;

    public Rezervacija() {
    }


    public Rezervacija(Long id, Long idKorisnika, Long idPutovanja, Integer brojPutnika) {
        this.id = id;
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
        this.brojPutnika = brojPutnika;
    }

    public Rezervacija(Long id, Long idKorisnika, Long idPutovanja, Integer brojPutnika, Double cena) {
        this.id = id;
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
        this.brojPutnika = brojPutnika;
        this.cena = cena;
    }

    public Rezervacija(Long idKorisnika, Long idPutovanja, Integer brojPutnika, Double cena) {
        this.idKorisnika = idKorisnika;
        this.idPutovanja = idPutovanja;
        this.brojPutnika = brojPutnika;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(Integer brojPutnika) {
        this.brojPutnika = brojPutnika;
    }
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}