package com.itycu.server.app.dto;


import lombok.Data;

@Data
public class ZcInfoListDTO {


    /**
     * 每次分页页数
     */
    private int page = 1;

    /**
     * 每次分页限定多少条
     */
    private int limit = 20;

    /**
     * 查询的关键字
     */
    private String keyword;


    /**
     * 获取的token验证数据
     */
    private String token;


}
