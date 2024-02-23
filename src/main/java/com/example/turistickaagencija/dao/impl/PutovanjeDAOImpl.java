package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.PutovanjeDAO;
import com.example.turistickaagencija.dto.AkcijeDTO;
import com.example.turistickaagencija.dto.PretragaPutovanjaDTO;
import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;
import com.example.turistickaagencija.model.Putovanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PutovanjeDAOImpl implements PutovanjeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class PutovanjeRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Putovanje> putovanja = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String prevozno_sredstvo = resultSet.getString(index++);
            String smestajna_jedinica = resultSet.getString(index++);
            String naziv_destinacije = resultSet.getString(index++);
            String slika_lokacije = resultSet.getString(index++);
            String datum_polaska = resultSet.getString(index++);
            String datum_povratka = resultSet.getString(index++);
            int broj_nocenja = resultSet.getInt(index++);
            float cena_aranzmana = resultSet.getFloat(index++);
            int ukupan_broj_mesta = resultSet.getInt(index++);
            int broj_slobodnih_mesta = resultSet.getInt(index++);
            double procenat_popusta = resultSet.getDouble(index++);
            String datum_vazenja_popusta = resultSet.getString(index++);
            String naziv_kategorije = resultSet.getString(index++);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


            LocalDateTime trenutniDatum = LocalDateTime.now();

            Putovanje putovanje = putovanja.get(id);
            if (putovanje == null) {
                putovanje = new Putovanje(id, PrevoznoSredstvo.valueOf(prevozno_sredstvo), SmestajnaJedinica.valueOf(smestajna_jedinica), naziv_destinacije, slika_lokacije, datum_polaska, datum_povratka, broj_nocenja, cena_aranzmana, ukupan_broj_mesta, broj_slobodnih_mesta);
                if(datum_vazenja_popusta != null) {
                    LocalDateTime date_vazenja_popusta = LocalDateTime.parse(datum_vazenja_popusta, formatter);
                    if(date_vazenja_popusta.isAfter(trenutniDatum)) {
                        putovanje.setProcenatPopusta(procenat_popusta);
                        putovanje.setDatumVazenjaPopusta(datum_vazenja_popusta);
                    } else {
                        putovanje.setProcenatPopusta(null);
                        putovanje.setDatumVazenjaPopusta(null);
                    }
                }
                putovanje.setNazivKategorije(naziv_kategorije);
                putovanja.put(putovanje.getId(), putovanje);
            }
        }

        public List<Putovanje> getPutovanja() {
            return new ArrayList<>(putovanja.values());
        }
        public List<Putovanje> getPutovanjaWhereFreeSpace() {
            List<Putovanje> putovanjaWhereFreeSpace = new ArrayList<>();
            for (Putovanje putovanje:putovanja.values()) {
                if(putovanje.getBrojSlobodnihMesta() > 0)
                    putovanjaWhereFreeSpace.add(putovanje);
            }
            return putovanjaWhereFreeSpace;
        }
    }

    @Override
    public List<Putovanje> findAll() {
        String sql =
                "SELECT pt.id, pt.prevozno_sredstvo, pt.smestajna_jedinica, pt.naziv_destinacije, pt.slika_lokacije, pt.datum_polaska, pt.datum_povratka, pt.broj_nocenja, pt.cena_aranzmana, pt.ukupan_broj_mesta, pt.broj_slobodnih_mesta, pt.procenat_popusta, pt.datum_vazenja_popusta, pt.naziv_kategorije FROM Putovanje pt " +
                        "ORDER BY pt.id";

        PutovanjeRowCallBackHandler rowCallbackHandler = new PutovanjeRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getPutovanja();
    }

    @Transactional
    @Override
    public int save(Putovanje putovanje) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO putovanje (prevozno_sredstvo, smestajna_jedinica, naziv_destinacije, slika_lokacije, datum_polaska, datum_povratka, broj_nocenja, cena_aranzmana, ukupan_broj_mesta, broj_slobodnih_mesta, naziv_kategorije) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, putovanje.getPrevoznoSredstvo().toString());
                preparedStatement.setString(index++, putovanje.getSmestajnaJedinica().toString());
                preparedStatement.setString(index++, putovanje.getNazivDestinacije());
                preparedStatement.setString(index++, putovanje.getSlikaLokacije());
                preparedStatement.setString(index++, putovanje.getDatumVremePolaska());
                preparedStatement.setString(index++, putovanje.getDatumVremePovratka());
                preparedStatement.setInt(index++, putovanje.getBrojNocenja());
                preparedStatement.setDouble(index++, putovanje.getCenaAranzmana());
                preparedStatement.setInt(index++, putovanje.getUkupanBrojMesta());
                preparedStatement.setInt(index++, putovanje.getBrojSlobodnihMesta());
                preparedStatement.setString(index++, putovanje.getNazivKategorije());

                return preparedStatement;
            }

        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }



    @Transactional
    @Override
    public void update(Putovanje putovanje) {
        String sql;
        if(putovanje.getSlikaLokacije() != null) {
            sql = "UPDATE putovanje SET prevozno_sredstvo = ?, smestajna_jedinica = ?, naziv_destinacije = ?, slika_lokacije = ?, datum_polaska = ?, datum_povratka = ?, broj_nocenja = ?, cena_aranzmana = ?, ukupan_broj_mesta = ?, broj_slobodnih_mesta = ?, naziv_kategorije = ? WHERE id = ?";
            jdbcTemplate.update(sql, putovanje.getPrevoznoSredstvo().toString(), putovanje.getSmestajnaJedinica().toString(), putovanje.getNazivDestinacije(), putovanje.getSlikaLokacije(), putovanje.getDatumVremePolaska(), putovanje.getDatumVremePovratka(), putovanje.getBrojNocenja(), putovanje.getCenaAranzmana(), putovanje.getUkupanBrojMesta(), putovanje.getBrojSlobodnihMesta(), putovanje.getNazivKategorije(), putovanje.getId());

        }else {
            sql = "UPDATE putovanje SET prevozno_sredstvo = ?, smestajna_jedinica = ?, naziv_destinacije = ?, datum_polaska = ?, datum_povratka = ?, broj_nocenja = ?, cena_aranzmana = ?, ukupan_broj_mesta = ?, broj_slobodnih_mesta = ?, naziv_kategorije = ? WHERE id = ?";
            jdbcTemplate.update(sql, putovanje.getPrevoznoSredstvo().toString(), putovanje.getSmestajnaJedinica().toString(), putovanje.getNazivDestinacije(), putovanje.getDatumVremePolaska(), putovanje.getDatumVremePovratka(), putovanje.getBrojNocenja(), putovanje.getCenaAranzmana(), putovanje.getUkupanBrojMesta(), putovanje.getBrojSlobodnihMesta(), putovanje.getNazivKategorije(), putovanje.getId());

        }
    }

    @Override
    public Putovanje findOne(Long id) {
        String sql =
                "SELECT * FROM putovanje kor " +
                        "WHERE kor.id = ? " +
                        "ORDER BY kor.id";

        PutovanjeRowCallBackHandler rowCallbackHandler = new PutovanjeRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        return rowCallbackHandler.getPutovanja().get(0);
    }

    @Override
    public Putovanje findOne(String nazivDestinacije, String datumVremePolaska, String datumVremePovratka) {
        String sql =
                "SELECT * FROM putovanje put " +
                        "WHERE put.naziv_destinacije = ? AND put.datum_polaska = ? AND put.datum_povratka = ?";

        PutovanjeRowCallBackHandler rowCallbackHandler = new PutovanjeRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, nazivDestinacije, datumVremePolaska, datumVremePovratka);

        return rowCallbackHandler.getPutovanja().get(0);
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM putovanje WHERE id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public void updateAkcije(AkcijeDTO akcijeDTO) {
        String sql = "UPDATE putovanje SET procenat_popusta = ?, datum_vazenja_popusta = ? WHERE id = ?";
        jdbcTemplate.update(sql, akcijeDTO.getPopust(), akcijeDTO.getDatumVazenjaPopusta(), akcijeDTO.getId());
    }

    @Override
    public List<Putovanje> findAllWhereFreeSpace() {
        String sql =
                "SELECT pt.id, pt.prevozno_sredstvo, pt.smestajna_jedinica, pt.naziv_destinacije, pt.slika_lokacije, pt.datum_polaska, pt.datum_povratka, pt.broj_nocenja, pt.cena_aranzmana, pt.ukupan_broj_mesta, pt.broj_slobodnih_mesta, pt.procenat_popusta, pt.datum_vazenja_popusta, pt.naziv_kategorije FROM Putovanje pt " +
                        "ORDER BY pt.id";

        PutovanjeRowCallBackHandler rowCallbackHandler = new PutovanjeRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getPutovanjaWhereFreeSpace();
    }

    @Override
    public void dodajSlobodnaMesta(Long idPutovanja, Integer brojPutnika) {
        String sql = "UPDATE putovanje SET broj_slobodnih_mesta = broj_slobodnih_mesta + ? WHERE id = ?";
        jdbcTemplate.update(sql, brojPutnika, idPutovanja);
    }
}