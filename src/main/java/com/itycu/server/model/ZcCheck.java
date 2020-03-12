package com.itycu.server.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZcCheck extends BaseEntity<Long> {

    private String checkDeptId;

    private String checkDeptName;

    private Long checkUserId;

    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date checkTime;

    private Integer status;

    private Integer del;

    private String bz;

    private Long createBy;

    private Long updateBy;

    private int result;

    private int total;

    private int zcCheckItemNum;

    private List<ZcCheckItem> zcCheckItemList;

    /** 再次盘点 0 初次盘点  1是再吃盘点 */
    private int reCheck;

    /** 追溯号码 */
    private String zsNum;

    /** 资产编号 */
    private String zcNum;

    /** 卡片编码 */
    private String kpBm;

    /** 是否盘盈 0否  1是 */
    private int profit;

    private String createUserName;

    private String deptName;

    private String check_num;

    private int normal;

    private int error;

    private String checkUserName;

    private List<ZcCheckItem> checkItemList;

    /** 盘点编号 */
    private int bh;

    /** 制单人（多表关联查询字段）*/
    private String creator;

    @Override
    public String toString() {
        return "ZcCheck{" +
                "checkDeptId='" + checkDeptId + '\'' +
                ", checkDeptName='" + checkDeptName + '\'' +
                ", checkUserId=" + checkUserId +
                ", checkTime=" + checkTime +
                ", status=" + status +
                ", del=" + del +
                ", bz='" + bz + '\'' +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", result=" + result +
                ", total=" + total +
                ", zcCheckItemNum=" + zcCheckItemNum +
                ", zcCheckItemList=" + zcCheckItemList +
                ", reCheck=" + reCheck +
                ", zsNum='" + zsNum + '\'' +
                ", zcNum='" + zcNum + '\'' +
                ", kpBm='" + kpBm + '\'' +
                ", profit=" + profit +
                ", createUserName='" + createUserName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", check_num='" + check_num + '\'' +
                ", normal=" + normal +
                ", error=" + error +
                ", checkUserName='" + checkUserName + '\'' +
                ", checkItemList=" + checkItemList +
                ", bh=" + bh +
                ", creator='" + creator + '\'' +
                '}';
    }
}
