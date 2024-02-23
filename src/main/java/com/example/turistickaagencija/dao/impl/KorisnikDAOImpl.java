package com.example.turistickaagencija.dao.impl;

import com.example.turistickaagencija.dao.KorisnikDAO;
import com.example.turistickaagencija.enums.Uloga;
import com.example.turistickaagencija.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KorisnikDAOImpl implements KorisnikDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class KorisnikRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Korisnik> korisnici = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String ime = resultSet.getString(index++);
            String prezime = resultSet.getString(index++);
            String email = resultSet.getString(index++);
            String lozinka = resultSet.getString(index++);
            String datumRodjenja = resultSet.getString(index++);
            String jmbg = resultSet.getString(index++);
            String adresa = resultSet.getString(index++);
            String brtelefona = resultSet.getString(index++);
            String datumprijave = resultSet.getString(index++);
            String uloga = resultSet.getString(index++);
            Korisnik korisnik = korisnici.get(id);
            if (korisnik == null) {
                korisnik = new Korisnik(id, ime, prezime, email, lozinka, datumRodjenja,jmbg,adresa,brtelefona, datumprijave,Uloga.valueOf(uloga));
                korisnici.put(korisnik.getId(), korisnik);
            }
        }

        public List<Korisnik> getKorisnici() {
            return new ArrayList<>(korisnici.values());
        }

    }
    @Transactional
    @Override
    public int save(Korisnik korisnik) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO korisnici (ime, prezime, email, lozinka,datumRodjenja,jmbg,adresa,brtelefona,datumprijave,uloga) VALUES (?, ?, ?, ?,?,?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, korisnik.getIme());
                preparedStatement.setString(index++, korisnik.getPrezime());
                preparedStatement.setString(index++, korisnik.getEmail());
                preparedStatement.setString(index++, korisnik.getLozinka());
                preparedStatement.setString(index++, korisnik.getDatumRodjenja().toString());
                preparedStatement.setString(index++, korisnik.getJmbg());
                preparedStatement.setString(index++, korisnik.getAdresa());
                preparedStatement.setString(index++, korisnik.getBrTelefona());
                preparedStatement.setString(index++, korisnik.getDatumPrijave().toString());
                preparedStatement.setString(index++, korisnik.getUloga().name());
                return preparedStatement;
            }

        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }


    @Override
    public Korisnik findOne(String email) {
        String sql =
                "SELECT kor.id, kor.ime, kor.prezime, kor.email, kor.lozinka, kor.datumRodjenja, kor.jmbg, kor.adresa, kor.brTelefona, kor.datumPrijave, kor.uloga FROM korisnici kor " +
                        "WHERE kor.email = ? " +
                        "ORDER BY kor.id";

        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, email);

        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Override
    public Korisnik findOne(Long id) {
        String sql =
                "SELECT kor.id, kor.ime, kor.prezime, kor.email, kor.lozinka, kor.datumRodjenja, kor.jmbg, kor.adresa, kor.brtelefona, kor.datumprijave, kor.uloga FROM korisnici kor " +
                        "WHERE kor.id = ? " +
                        "ORDER BY kor.id";

        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);

        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Override
    public Korisnik findOne(String email, String sifra) {
        String sql =
                "SELECT kor.id, kor.ime, kor.prezime, kor.email, kor.lozinka, kor.datumRodjenja, kor.jmbg, kor.adresa, kor.brTelefona, kor.datumPrijave, kor.uloga FROM korisnici kor " +
                        "WHERE kor.email = ? AND " +
                        "kor.lozinka = ? " +
                        "ORDER BY kor.id";

        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, email, sifra);

        if(rowCallbackHandler.getKorisnici().size() == 0) {
            return null;
        }

        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Transactional
    @Override
    public int update(Korisnik korisnik) {
        String sql = "UPDATE korisnici SET ime = ?, prezime = ?, email = ?, datumrodjenja = ?, jmbg = ?, adresa = ?, brTelefona = ? WHERE id = ?";
        boolean uspeh = jdbcTemplate.update(sql, korisnik.getIme() , korisnik.getPrezime(), korisnik.getEmail(), korisnik.getDatumRodjenja(), korisnik.getJmbg(), korisnik.getAdresa(), korisnik.getBrTelefona(), korisnik.getId()) == 1;

        return uspeh?1:0;
    }

    @Override
    public int updatePassword(String sifra, Long id) {
        String sql = "UPDATE korisnici SET lozinka = ? WHERE id = ?";
        boolean uspeh = jdbcTemplate.update(sql, sifra, id) == 1;

        return uspeh?1:0;
    }
}
