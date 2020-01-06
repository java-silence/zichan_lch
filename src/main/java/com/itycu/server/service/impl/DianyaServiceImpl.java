package com.itycu.server.service.impl;

import com.itycu.server.dao.DianyaDao;
import com.itycu.server.service.DianyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class DianyaServiceImpl implements DianyaService {


    @Autowired
    private DianyaDao dianyaDao;

    @Override
    public BigDecimal getDianYaAverage() {
        dianyaDao.getDianYaAverage();
        return null;
    }
}
