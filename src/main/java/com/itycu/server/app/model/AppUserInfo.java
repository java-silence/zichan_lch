package com.itycu.server.app.model;

import lombok.Data;

import java.util.Date;

@Data
public class AppUserInfo {
    private Long id;
    private String createTime;
    private String updateTime;
    private String username;
    private String nickname;
    private String headImgUrl;
    private String phone;
    private String email;
    private Date birthday;
    private int sex;
    private int status;
    private String memo;
    private String c03;
    private Long deptId;
}
