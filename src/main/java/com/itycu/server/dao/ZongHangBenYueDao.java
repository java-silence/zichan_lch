package com.itycu.server.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author lch
 * @create 2020-03-23 18:16
 */
@Mapper
public interface ZongHangBenYueDao {

    int countMonthBaoxiuCount();

    int countMonthCaigouCount();

    int countMonthChuzhiCount();

    int countMonthDiaoboCount();

    int countMonthPandianCount();

    int countMonthXunjianCount();
}
