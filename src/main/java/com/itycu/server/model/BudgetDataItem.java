package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)实体类
 *
 * @author makejava
 * @since 2020-03-18 09:55:10
 */
@Data
public class BudgetDataItem implements Serializable {
    private static final long serialVersionUID = 643027673464907598L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 预算周期
    */
    private String budgetPeriod;
    /**
    * 预算类别 
    */
    private String budgetType;
    /**
    * 预算种类  YEAR 年度预算 MONTH 月度预算
    */
    private String budgetKind;
    /**
    * 数量
    */
    private Integer budgetNum;
    /**
    * 预算单价
    */
    private Double budgetPrice;
    /**
    * 资产名称
    */
    private String property;
    /**
    * 单位
    */
    private String budgetUnit;
    /**
    * 预算金额
    */
    private Double budgetMoney;
    /**
    * 管理部门id
    */
    private Integer budgetManagerDeptId;
    /**
    * 管理部门名称
    */
    private String budgetManagerName;
    /**
    * 品牌
    */
    private String brand;
    /**
    * 规格型号
    */
    private String model;
    /**
    * 文件附件
    */
    private String fileUrl;
    /**
    * 备注
    */
    private String remark;
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
    * 更新时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    /**
    * 预算状态
    */
    private Integer budgetStatus;
    /**
    * 预算部门id
    */
    private Integer budgetDeptId;
    /**
    * 预算部门名称
    */
    private String budgetDeptName;
    /**
    * 预算预留字段1
    */
    private String budgetC01;
    /**
    * 预算预留字段2
    */
    private String budgetC02;
    /**
    * 预算预留字段3
    */
    private String budgetC03;


    /**
     * 预算列别名称
     */
    private String typeName;

    /**
     * 预算购买单号
     */
    private String budgetDataId;


}