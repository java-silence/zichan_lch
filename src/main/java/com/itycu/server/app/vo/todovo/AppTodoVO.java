package com.itycu.server.app.vo.todovo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lch
 * @create 2020-03-16 10:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class AppTodoVO implements Serializable {

    private Long id;

    private String biaoti;

    private Long bizid;

    private Long flowid;

    private Long stepid;

    private String url;

    private String status;

    /** 用户名 */
    private String username;

    private String nickname;

    /** 申请部门 */
    private String applyDeptName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 类型. */
    private Integer type;

    private Long auditby;

    private Long sendby;

    private String neirong;

    private String biztype;

    private String biztable;

    private Long bizcreateby;

    private Long bizdeptid;

    private String memo;

    private String c01;

    private String c02;

    private String c03;

    /** 审核待办集合. */
    private String todoIds;


}
