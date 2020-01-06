package com.itycu.server.dto;

import com.itycu.server.model.Repairs;

/**
 * Created by fanlinglong on 2018/8/11.
 */
public class RepairsVO extends Repairs {
    String invname;        //系统分类

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }
}
