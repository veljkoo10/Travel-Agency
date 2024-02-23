package com.example.turistickaagencija.model;

public class Komentar {
    private Long id;
    private Long id_kreatora;
    private Long id_primaoca;
    private Long id_putovanja;
    private String komentar;

    public Komentar(Long id, Long id_kreatora, Long id_primaoca, String komentar) {
        this.id = id;
        this.id_kreatora = id_kreatora;
        this.id_primaoca = id_primaoca;
        this.komentar = komentar;
    }

    public Komentar(Long id, Long id_kreatora, Long id_primaoca, Long id_putovanja, String komentar) {
        this.id = id;
        this.id_kreatora = id_kreatora;
        this.id_primaoca = id_primaoca;
        this.id_putovanja = id_putovanja;
        this.komentar = komentar;
    }

    public Komentar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_kreatora() {
        return id_kreatora;
    }

    public void setId_kreatora(Long id_kreatora) {
        this.id_kreatora = id_kreatora;
    }

    public Long getId_primaoca() {
        return id_primaoca;
    }

    public void setId_primaoca(Long id_primaoca) {
        this.id_primaoca = id_primaoca;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Long getId_putovanja() {
        return id_putovanja;
    }

    public void setId_putovanja(Long id_putovanja) {
        this.id_putovanja = id_putovanja;
    }
}
