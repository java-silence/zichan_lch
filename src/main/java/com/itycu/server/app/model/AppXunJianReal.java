package com.itycu.server.app.model;

import lombok.Data;

import java.util.Date;


@Data
public class AppXunJianReal {


    private int id;

    private int zcInspectId;

    private int zcId;

    private Date createTime;

    private int result;

    private int status;


}
