package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.KomentarDAO;
import com.example.turistickaagencija.model.Komentar;
import com.example.turistickaagencija.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseKomentarServiceImpl implements KomentarService {
    @Autowired
    private KomentarDAO komentarDAO;
    @Override
    public int save(Komentar komentar) {
        return komentarDAO.save(komentar);
    }

    @Override
    public List<Komentar> findAllForUser(Long korisnik) {
        return komentarDAO.findAllForUser(korisnik);
    }
}
