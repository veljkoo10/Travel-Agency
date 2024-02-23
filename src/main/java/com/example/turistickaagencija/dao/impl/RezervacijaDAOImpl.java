package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.PutovanjeDAO;
import com.example.turistickaagencija.dao.RezervacijaDAO;
import com.example.turistickaagencija.model.Putovanje;
import com.example.turistickaagencija.model.Rezervacija;
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
public class RezervacijaDAOImpl implements RezervacijaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    PutovanjeDAO putovanjeDAO;

    private class RezervacijaRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Rezervacija> rezervacije = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            Long id_korisnika = resultSet.getLong(index++);
            Long id_putovanja = resultSet.getLong(index++);
            Integer broj_putnika = resultSet.getInt(index++);


            Rezervacija rezervacija = rezervacije.get(id);
            if (rezervacija == null) {
                rezervacija = new Rezervacija(id, id_korisnika, id_putovanja, broj_putnika);
                rezervacije.put(rezervacija.getId(), rezervacija);
            }
        }

        public List<Rezervacija> getRezervacije() {
            return new ArrayList<>(rezervacije.values());
        }
    }

    @Transactional
    @Override
    public int save(Rezervacija rezervacija) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            Putovanje putovanje = putovanjeDAO.findOne(rezervacija.getIdPutovanja());
            Double cena = putovanje.TrenutnaCena();

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO rezervacija (id_korisnika, id_putovanja, broj_putnika, cena) VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setLong(index++, rezervacija.getIdKorisnika());
                preparedStatement.setLong(index++, rezervacija.getIdPutovanja());
                preparedStatement.setInt(index++, rezervacija.getBrojPutnika());
                preparedStatement.setDouble(index++, cena);
                return preparedStatement;
            }

        };
        Putovanje putovanje = putovanjeDAO.findOne(rezervacija.getIdPutovanja());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        putovanje.setBrojSlobodnihMesta(putovanje.getBrojSlobodnihMesta() - rezervacija.getBrojPutnika());
        putovanjeDAO.update(putovanje);
        return uspeh?1:0;
    }

    @Override
    public Rezervacija findOneByPutovanjeId(Long id) {
        String sql = "SELECT * FROM rezervacija WHERE id_putovanja = ?";
        RezervacijaRowCallBackHandler rowCallbackHandler = new RezervacijaRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        if(rowCallbackHandler.getRezervacije().size() == 0) {
            return null;
        }

        return rowCallbackHandler.getRezervacije().get(0);
    }

    @Override
    public List<Rezervacija> findAllForUser(Long idKorisnika) {
        String sql =
                "SELECT * FROM rezervacija WHERE id_korisnika = ?";


        RezervacijaRowCallBackHandler rowCallbackHandler = new RezervacijaRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, idKorisnika);

        return rowCallbackHandler.getRezervacije();
    }

    @Override
    public Rezervacija findOne(Long id) {
        String sql =
                "SELECT * FROM rezervacija rez " +
                        "WHERE rez.id = ?";

        RezervacijaRowCallBackHandler rowCallbackHandler = new RezervacijaRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        return rowCallbackHandler.getRezervacije().get(0);
    }

    @Override
    public void delete(Long aLong) {
        String sql = "DELETE FROM rezervacija WHERE id = ?";
        jdbcTemplate.update(sql, aLong);
    }
}
