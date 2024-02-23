package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.PutovanjeZahtevDAO;
import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;
import com.example.turistickaagencija.model.PutovanjeZahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PutovanjeZahtevDAOImpl implements PutovanjeZahtevDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class PutovanjeZahtevRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, PutovanjeZahtev> putovanja = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String prevozno_sredstvo = resultSet.getString(index++);
            String naziv_destinacije = resultSet.getString(index++);
            String datum_polaska = resultSet.getString(index++);
            String datum_povratka = resultSet.getString(index++);
            int ukupan_broj_mesta = resultSet.getInt(index++);
            Long id_korisnika = resultSet.getLong(index++);
            String smestajna_jedinica = resultSet.getString(index++);
            int broj_nocenja = resultSet.getInt(index++);
            double cena_aranzmana = resultSet.getDouble(index++);
            int je_revidirano = resultSet.getInt(index++);
            int je_rezervisano = resultSet.getInt(index++);
            Long id_menadzera = resultSet.getLong(index++);

            PutovanjeZahtev putovanjeZahtev = putovanja.get(id);
            if (putovanjeZahtev == null) {
                putovanjeZahtev = new PutovanjeZahtev(id, PrevoznoSredstvo.valueOf(prevozno_sredstvo), naziv_destinacije, datum_polaska, datum_povratka, ukupan_broj_mesta, id_korisnika);
                if(smestajna_jedinica != null)
                    putovanjeZahtev.setSmestajnaJedinica(SmestajnaJedinica.valueOf(smestajna_jedinica));
                if(broj_nocenja != 0)
                    putovanjeZahtev.setBrojNocenja(broj_nocenja);
                if(cena_aranzmana != 0)
                    putovanjeZahtev.setCenaAranzmana(cena_aranzmana);
                if(id_menadzera != null)
                    putovanjeZahtev.setIdMenadzera(id_menadzera);
                if(je_revidirano==1)
                    putovanjeZahtev.setRevidirano(true);
                else
                    putovanjeZahtev.setRevidirano(false);
                if(je_rezervisano==1)
                    putovanjeZahtev.setRezervisano(true);
                else
                    putovanjeZahtev.setRezervisano(false);

                putovanja.put(putovanjeZahtev.getId(), putovanjeZahtev);
            }
        }

        public List<PutovanjeZahtev> getPutovanja() {
            return new ArrayList<>(putovanja.values());
        }
    }
    @Transactional
    @Override
    public int save(PutovanjeZahtev putovanjeZahtev) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO putovanje_zahtev (prevozno_sredstvo, naziv_destinacije, datum_polaska, datum_povratka, ukupan_broj_mesta, id_korisnika, je_revidirano) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, putovanjeZahtev.getPrevoznoSredstvo().toString());
                preparedStatement.setString(index++, putovanjeZahtev.getNazivDestinacije());
                preparedStatement.setString(index++, putovanjeZahtev.getDatumVremePolaska());
                preparedStatement.setString(index++, putovanjeZahtev.getDatumVremePovratka());
                preparedStatement.setInt(index++, putovanjeZahtev.getBrojPutnika());
                preparedStatement.setDouble(index++, putovanjeZahtev.getIdKorisnika());
                preparedStatement.setInt(index++, 0);

                return preparedStatement;
            }

        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }

    @Override
    public PutovanjeZahtev findOne(Long id) {
        String sql =
                "SELECT * FROM putovanje_zahtev WHERE id = ?";

        PutovanjeZahtevRowCallBackHandler rowCallbackHandler = new PutovanjeZahtevRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        return rowCallbackHandler.getPutovanja().get(0);
    }

    @Override
    public List<PutovanjeZahtev> findAll() {
        String sql =
                "SELECT * FROM putovanje_zahtev WHERE je_revidirano != 1 ";

        PutovanjeZahtevRowCallBackHandler rowCallbackHandler = new PutovanjeZahtevRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getPutovanja();
    }

    @Override
    public void saveProposition(PutovanjeZahtev putovanjeZahtev) {
        String sql = "UPDATE putovanje_zahtev SET prevozno_sredstvo = ?, smestajna_jedinica = ?, naziv_destinacije = ?, datum_polaska = ?, datum_povratka = ?, broj_nocenja = ?, cena_aranzmana = ?, ukupan_broj_mesta = ?, id_korisnika = ?, id_menadzera = ?, je_revidirano = 1 WHERE id = ?";
        jdbcTemplate.update(sql, putovanjeZahtev.getPrevoznoSredstvo().toString(), putovanjeZahtev.getSmestajnaJedinica().toString(), putovanjeZahtev.getNazivDestinacije(), putovanjeZahtev.getDatumVremePolaska(), putovanjeZahtev.getDatumVremePovratka(),
                putovanjeZahtev.getBrojNocenja(), putovanjeZahtev.getCenaAranzmana(), putovanjeZahtev.getBrojPutnika(), putovanjeZahtev.getIdKorisnika(), putovanjeZahtev.getIdMenadzera(), putovanjeZahtev.getId());
    }

    @Override
    public void delete(PutovanjeZahtev putovanje) {
        String sql = "DELETE FROM putovanje_zahtev WHERE id = ?";
        jdbcTemplate.update(sql, putovanje.getId());
    }

    @Override
    public List<PutovanjeZahtev> findAllForKupac(Long id) {
        String sql =
                "SELECT * FROM putovanje_zahtev WHERE id_korisnika = ? AND je_revidirano = 1 AND je_rezervisano = 0";


        PutovanjeZahtevRowCallBackHandler rowCallbackHandler = new PutovanjeZahtevRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        return rowCallbackHandler.getPutovanja();
    }

    @Override
    public void rezervisi(Long id) {
        String sql = "UPDATE putovanje_zahtev SET je_rezervisano = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void revidiranje(PutovanjeZahtev putovanjeZahtev) {
        String sql = "UPDATE putovanje_zahtev SET je_revidirano = 0 WHERE id = ?";
        jdbcTemplate.update(sql, putovanjeZahtev.getId());
    }
}
