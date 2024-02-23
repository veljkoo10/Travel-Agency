package com.example.turistickaagencija.dto;

public class OdbijanjeZahtevaDTO {
    private Long id;
    private String komentar;

    private Long idKupca;

    public OdbijanjeZahtevaDTO(Long id, String komentar) {
        this.id = id;
        this.komentar = komentar;
    }

    public OdbijanjeZahtevaDTO(Long id, String komentar, Long idKupca) {
        this.id = id;
        this.komentar = komentar;
        this.idKupca = idKupca;
    }

    public OdbijanjeZahtevaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Long getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(Long idKupca) {
        this.idKupca = idKupca;
    }
}
