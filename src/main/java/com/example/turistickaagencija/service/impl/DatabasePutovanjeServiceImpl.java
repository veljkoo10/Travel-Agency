package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.PutovanjeDAO;
import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.dto.RezervacijaProfilDTO;
import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.model.Rezervacija;
import com.example.turistickaagencija.service.PutovanjeService;
import com.example.turistickaagencija.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class DatabasePutovanjeServiceImpl implements PutovanjeService {
    @Autowired
    private PutovanjeDAO putovanjeDAO;

    @Autowired
    private RezervacijaService rezervacijaService;
    @Override
    public List<Putovanje> findAll() {
        return putovanjeDAO.findAll();
    }

    @Override
    public Putovanje findOne(Long id) {
        return putovanjeDAO.findOne(id);
    }

    @Override
    public Putovanje save(Putovanje putovanje) {
        putovanjeDAO.save(putovanje);
        return putovanje;
    }

    @Override
    public Putovanje update(Putovanje putovanje) {
        putovanjeDAO.update(putovanje);
        return putovanje;
    }


    @Override
    public Putovanje findOne(String nazivDestinacije, String datumVremePolaska, String datumVremePovratka) {
        return putovanjeDAO.findOne(nazivDestinacije, datumVremePolaska, datumVremePovratka);
    }

    @Override
    public boolean delete(Long id) {
        if(rezervacijaService.isReservatedTrip(id))
            return false;
        return putovanjeDAO.delete(id);
    }

    @Override
    public void updateAkcije(AkcijeDTO akcijeDTO) {
        putovanjeDAO.updateAkcije(akcijeDTO);
    }

    @Override
    public List<Putovanje> findAllWhereFreeSpace() {
        return putovanjeDAO.findAllWhereFreeSpace();
    }

    @Override
    public List<Putovanje> findAllWhereFreeSpaceSpecial() {
        List<Putovanje> putovanjaSaSlobodnimMestom = putovanjeDAO.findAllWhereFreeSpace();
        List<Putovanje> putovanjaNaAkciji = getPutovanjaNaAkciji(putovanjaSaSlobodnimMestom);
        if(putovanjaNaAkciji.size() > 0)
            return randomNPutovanja(putovanjaNaAkciji);
        List<Putovanje> putovanjaUSezoni = getSezonskaPutovanja(putovanjaSaSlobodnimMestom);
        if(putovanjaUSezoni.size() > 0)
            return randomNPutovanja(putovanjaUSezoni);
        return randomNPutovanja(putovanjaSaSlobodnimMestom);
    }

    @Override
    public List<RezervacijaProfilDTO> findAllReservationsForUser(Long idKorisnika) {
        List<Rezervacija> sveRezervacije = rezervacijaService.findAllForUser(idKorisnika);
        List<Putovanje> svaPutovanja = findAll();
        List<RezervacijaProfilDTO> rezervacije = new ArrayList<>();
        for(Rezervacija rezervacija:sveRezervacije) {
            for(Putovanje putovanje: svaPutovanja) {
                if(rezervacija.getIdPutovanja() == putovanje.getId())
                    rezervacije.add(new RezervacijaProfilDTO(rezervacija.getId(), putovanje.getNazivDestinacije(), putovanje.getDatumVremePolaska(), putovanje.getDatumVremePovratka(), rezervacija.getBrojPutnika(), putovanje.TrenutnaCena()));
            }
        }
        return rezervacije;
    }

    @Override
    public void dodajSlobodnaMesta(Long idPutovanja, Integer brojPutnika) {
        putovanjeDAO.dodajSlobodnaMesta(idPutovanja, brojPutnika);
    }

    public List<Putovanje> getPutovanjaNaAkciji(List<Putovanje> putovanja) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime trenutniDatum = LocalDateTime.now();
        List<Putovanje> akcijskePonude = new ArrayList<>();
        for (Putovanje putovanje: putovanja) {
            if(putovanje.getDatumVazenjaPopusta() != null){
                LocalDateTime date_vazenja_popusta = LocalDateTime.parse(putovanje.getDatumVazenjaPopusta(), formatter);
                if(date_vazenja_popusta.isAfter(trenutniDatum))
                    akcijskePonude.add(putovanje);
            }
        }
        return akcijskePonude;
    }

    public List<Putovanje> getSezonskaPutovanja(List<Putovanje> putovanja) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime trenutniDatum = LocalDateTime.now();
        List<Putovanje> akcijskePonude = new ArrayList<>();
        String sezona = odrediSezonu(trenutniDatum);
        if(sezona == null)
            return akcijskePonude;
        for (Putovanje putovanje: putovanja) {
            if(putovanje.getNazivKategorije().equals(sezona)){
                akcijskePonude.add(putovanje);
            }
        }
        return akcijskePonude;
    }

    public static List<Putovanje> randomNPutovanja(List<Putovanje> putovanja) {
        Random random = new Random();
        int size = putovanja.size();
        if(size == 1 || size == 0)
            return putovanja;
        List<Putovanje> shuffledList = new ArrayList<>(putovanja);
        Collections.shuffle(shuffledList);

        return shuffledList.subList(0, random.nextInt(1,size));
    }

    public static String odrediSezonu(LocalDateTime trenutniDatum) {
        int mesec = trenutniDatum.getMonthValue();

        if (mesec >= 6 && mesec <= 8) {
            // Meseci od juna do avgusta su letnji
            return "Letovanje";
        } else if (mesec >= 12 || (mesec >= 1 && mesec <= 2)) {
            // Meseci decembar, januar i februar su zimski
            return "Zimovanje";
        } else {
            // U suprotnom, nije ni leto ni zima
            return null;
        }
    }
}
