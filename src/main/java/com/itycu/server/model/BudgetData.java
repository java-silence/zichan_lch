package com.itycu.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)实体类
 *
 * @author makejava
 * @since 2020-03-18 09:55:10
 */
@Data
public class BudgetData implements Serializable {
    private static final long serialVersionUID = 61834331984962282L;
    /**
     * 主键ID
     */
    private int id;
    /**
     * 预算购买单号
     */
    private String budgetDataId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 申请部门ID
     */
    private Integer applyDeptId;
    /**
     * 申请部门名称
     */
    private String applyDeptName;
    /**
     * 管理部门Id
     */
    private String glDeptId;
    /**
     * 管理部门名称
     */
    private String glDeptName;
    /**
     * 申请时间
     */
    private Date applytime;
    /**
     * 流程ID
     */
    private Integer flowid;
    /**
     * 流程步骤
     */
    private Integer stepid;
    /**
     * 审核状态  0:待提交 1:审核中 2:审核完毕
     */
    private Integer status;
    /**
     * 类型 0:年度 1:月度
     */
    private Integer budgetKind;
    /**
     * 删除 0:正常 1:删除
     */
    private Integer del;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 审核意见
     */
    private String opinion;
    /**
     * 备注信息
     */
    private String mark;
    /**
     * 预留字段1
     */
    private String dataC01;
    /**
     * 预留字段2
     */
    private String dataC02;
    /**
     * 预留字段3
     */
    private String dataC03;

    /**
     * 流程步骤名称
     */
    private String stepname;


    private List<BudgetDataItem> budgetDataList;

}