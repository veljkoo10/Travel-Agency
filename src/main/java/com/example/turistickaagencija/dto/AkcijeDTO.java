package com.example.turistickaagencija.dto;

public class AkcijeDTO {
    Double popust;
    String datumVazenjaPopusta;
    Long id;

    public AkcijeDTO() {
    }

    public AkcijeDTO(Double popust, String datumVazenjaPopusta, Long id) {
        this.popust = popust;
        this.datumVazenjaPopusta = datumVazenjaPopusta;
        this.id = id;
    }

    public Double getPopust() {
        return popust;
    }

    public void setPopust(Double popust) {
        this.popust = popust;
    }

    public String getDatumVazenjaPopusta() {
        return datumVazenjaPopusta;
    }

    public void setDatumVazenjaPopusta(String datumVazenjaPopusta) {
        this.datumVazenjaPopusta = datumVazenjaPopusta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
