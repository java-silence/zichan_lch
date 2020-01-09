package com.itycu.server.app.dto.xunjian;


import lombok.Data;

@Data
public class XunJianSubmitDTO {


    /**
     * 外观
     */
    private String appearance;

    /**
     * 功能
     */
    private String funct;

    /**
     * 图片
     */
    private String img;

    /**
     * 意见
     */
    private String opinion;

    /**
     * 巡检结果
     */
    private String result;

    /**
     * epcid`
     */
    private String epcid;


    /**
     * 资产id
     */
    private String zcId;


}
