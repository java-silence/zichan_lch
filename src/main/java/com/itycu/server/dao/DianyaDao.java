package com.itycu.server.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;



@Mapper
public interface DianyaDao {

    void  getDianYaAverage();

}
