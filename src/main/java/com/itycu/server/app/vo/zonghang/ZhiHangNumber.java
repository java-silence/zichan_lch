package com.itycu.server.app.vo.zonghang;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 支行数据及所在的管理部门数据
 * @author lch
 * @create 2020-03-23 14:27
 */
@Data
@Slf4j
public class ZhiHangNumber implements Serializable {

    /** 部门名称 */
    private String detName;

    /** 部门名称 */
    private Long detId;

    /** 保卫部数量 */
    private Integer bwbZcCount = 0;

    /** 科技部数量 */
    private Integer kjbZcCount = 0;

    /** 综合办数量 */
    private Integer zhbZcCount = 0;

    /** 运营部数量 */
    private Integer yybZcCount = 0;

}
