package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.KategorijaPutovanjaDAO;
import com.example.turistickaagencija.enums.PrevoznoSredstvo;
import com.example.turistickaagencija.enums.SmestajnaJedinica;
import com.example.turistickaagencija.model.KategorijaPutovanja;
import com.example.turistickaagencija.model.Putovanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KategorijaPutovanjaDAOImpl implements KategorijaPutovanjaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class KategorijaPutovanjaRowCallBackHandler implements RowCallbackHandler {

        private Map<String, KategorijaPutovanja> kategorijePutovanja = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            String opis = resultSet.getString(index++);
            String naziv = resultSet.getString(index++);


            KategorijaPutovanja kategorijaPutovanja = kategorijePutovanja.get(naziv);
            if (kategorijaPutovanja == null) {
                kategorijaPutovanja = new KategorijaPutovanja(opis, naziv);
                kategorijePutovanja.put(kategorijaPutovanja.getNaziv(), kategorijaPutovanja);
            }
        }

        public List<KategorijaPutovanja> getKategorijePutovanja() {
            return new ArrayList<>(kategorijePutovanja.values());
        }

    }
    @Override
    public List<KategorijaPutovanja> findAll() {
        String sql =
                "SELECT kp.naziv, kp.opis FROM kategorija_smestaja ks ";

        KategorijaPutovanjaRowCallBackHandler rowCallbackHandler = new KategorijaPutovanjaRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getKategorijePutovanja();
    }
}