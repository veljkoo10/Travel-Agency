package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.KomentarDAO;
import com.example.turistickaagencija.model.Komentar;
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
public class KomentarDAOImpl implements KomentarDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class KomentarRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Komentar> komentari = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            Long id_kreatora = resultSet.getLong(index++);
            Long id_primaoca = resultSet.getLong(index++);
            String komentar = resultSet.getString(index++);


            Komentar komentarObj = komentari.get(id);
            if (komentarObj == null) {
                komentarObj = new Komentar(id, id_kreatora, id_primaoca, komentar);
                komentari.put(komentarObj.getId(), komentarObj);
            }
        }

        public List<Komentar> getKomentari() {
            return new ArrayList<>(komentari.values());
        }

    }

    @Transactional
    @Override
    public int save(Komentar komentar) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO komentari (id_kreatora, id_primaoca, komentar) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setLong(index++, komentar.getId_kreatora());
                preparedStatement.setLong(index++, komentar.getId_primaoca());
                preparedStatement.setString(index++, komentar.getKomentar());

                return preparedStatement;
            }

        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }

    @Override
    public List<Komentar> findAllForUser(Long korisnik) {
        String sql =
                "SELECT * FROM komentari WHERE id_primaoca = ?";


        KomentarRowCallBackHandler rowCallbackHandler = new KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, korisnik);

        return rowCallbackHandler.getKomentari();
    }
}
