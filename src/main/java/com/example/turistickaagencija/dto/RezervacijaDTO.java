package com.example.turistickaagencija.dto;

public class RezervacijaDTO {
    int idPutovanja;
    int brojPutnika;

    public RezervacijaDTO(int idPutovanja, int brojPutnika) {
        this.idPutovanja = idPutovanja;
        this.brojPutnika = brojPutnika;
    }

    public RezervacijaDTO() {
    }

    public int getIdPutovanja() {
        return idPutovanja;
    }

    public void setIdPutovanja(int idPutovanja) {
        this.idPutovanja = idPutovanja;
    }

    public int getBrojPutnika() {
        return brojPutnika;
    }

    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }
}
