package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.dao.KategorijaPutovanjaDAO;
import com.example.turistickaagencija.model.KategorijaPutovanja;
import com.example.turistickaagencija.service.KategorijaPutovanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class DatabaseKategorijaPutovanjaServiceImpl implements KategorijaPutovanjaService {
    @Autowired
    private KategorijaPutovanjaDAO kategorijaPutovanjaDAO;

    @Override
    public List<KategorijaPutovanja> findAll(){ return kategorijaPutovanjaDAO.findAll();}
}
