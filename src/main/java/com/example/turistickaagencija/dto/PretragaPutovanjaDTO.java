package com.example.turistickaagencija.dto;

public class PretragaPutovanjaDTO {
    String prevoznoSredstvo;
    String nazivDestinacije;
    Integer brojNocenja;
    Integer minimlnaCena;
    Integer maksimalnaCena;

    public PretragaPutovanjaDTO() {
    }

    public PretragaPutovanjaDTO(String prevoznoSredstvo, String nazivDestinacije, Integer brojNocenja, Integer minimlnaCena, Integer maksimalnaCena) {
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.nazivDestinacije = nazivDestinacije;
        this.brojNocenja = brojNocenja;
        this.minimlnaCena = minimlnaCena;
        this.maksimalnaCena = maksimalnaCena;
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

    public Integer getBrojNocenja() {
        return brojNocenja;
    }

    public void setBrojNocenja(Integer brojNocenja) {
        this.brojNocenja = brojNocenja;
    }

    public Integer getMinimlnaCena() {
        return minimlnaCena;
    }

    public void setMinimlnaCena(Integer minimlnaCena) {
        this.minimlnaCena = minimlnaCena;
    }

    public Integer getMaksimalnaCena() {
        return maksimalnaCena;
    }

    public void setMaksimalnaCena(Integer maksimalnaCena) {
        this.maksimalnaCena = maksimalnaCena;
    }
}