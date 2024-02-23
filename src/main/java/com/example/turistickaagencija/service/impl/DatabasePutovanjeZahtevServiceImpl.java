package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.PutovanjeZahtevDAO;
import com.example.turistickaagencija.model.Komentar;
import com.example.turistickaagencija.model.PutovanjeZahtev;
import com.example.turistickaagencija.service.KomentarService;
import com.example.turistickaagencija.service.PutovanjeZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabasePutovanjeZahtevServiceImpl implements PutovanjeZahtevService {
    @Autowired
    private PutovanjeZahtevDAO putovanjeZahtevDAO;

    @Autowired
    private KomentarService komentarService;

    @Override
    public PutovanjeZahtev save(PutovanjeZahtev putovanjeZahtev) {
        putovanjeZahtevDAO.save(putovanjeZahtev);
        return putovanjeZahtev;
    }

    @Override
    public PutovanjeZahtev findOne(Long id) {
        return putovanjeZahtevDAO.findOne(id);
    }

    @Override
    public List<PutovanjeZahtev> findAll() {
        return putovanjeZahtevDAO.findAll();
    }

    @Override
    public void saveProposition(PutovanjeZahtev putovanjeZahtev) {
        putovanjeZahtevDAO.saveProposition(putovanjeZahtev);
    }

    @Override
    public void delete(PutovanjeZahtev putovanje, String komentar, Long idMenadzera) {
        putovanjeZahtevDAO.delete(putovanje);
        Komentar komentarObj = new Komentar();
        komentarObj.setKomentar(komentar);
        komentarObj.setId_kreatora(idMenadzera);
        komentarObj.setId_primaoca(putovanje.getIdKorisnika());
        komentarObj.setId_putovanja(putovanje.getId());
        komentarService.save(komentarObj);
    }

    @Override
    public List<PutovanjeZahtev> findAllForKupac(Long id) {
        return putovanjeZahtevDAO.findAllForKupac(id);
    }

    @Override
    public void rezervisi(Long id) {
        putovanjeZahtevDAO.rezervisi(id);
    }

    @Override
    public void revidiraj(PutovanjeZahtev putovanjeZahtev, String komentar) {
        putovanjeZahtevDAO.revidiranje(putovanjeZahtev);
        Komentar komentarObj = new Komentar();
        komentarObj.setKomentar(komentar);
        komentarObj.setId_kreatora(putovanjeZahtev.getIdKorisnika());
        komentarObj.setId_putovanja(putovanjeZahtev.getId());
        komentarObj.setId_primaoca(putovanjeZahtev.getIdMenadzera());
        komentarService.save(komentarObj);
    }

    @Override
    public void deleteKupac(PutovanjeZahtev putovanjeZahtev, String komentar) {
        Komentar komentarObj = new Komentar();
        komentarObj.setId_putovanja(putovanjeZahtev.getId());
        komentarObj.setKomentar(komentar);
        komentarObj.setId_kreatora(putovanjeZahtev.getIdKorisnika());
        komentarObj.setId_primaoca(putovanjeZahtev.getIdMenadzera());
        komentarService.save(komentarObj);
        putovanjeZahtevDAO.delete(putovanjeZahtev);
    }
}
