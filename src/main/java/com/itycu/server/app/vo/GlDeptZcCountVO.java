package com.itycu.server.app.vo;


import lombok.Data;

/**
 * 首页管理部门的资产数据统计Vo
 */

@Data
public class GlDeptZcCountVO {

    /**
     * 管理部门身份
     */
    private String c03;


    /**
     * 资产数据统计
     */
    private int zcCount;


}
