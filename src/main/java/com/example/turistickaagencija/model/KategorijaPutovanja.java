package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enums.Kategorija;

public class KategorijaPutovanja {
    private String opis;
    private String naziv;

    public KategorijaPutovanja() {
    }

    public KategorijaPutovanja(String opis, String naziv) {
        this.opis = opis;
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
