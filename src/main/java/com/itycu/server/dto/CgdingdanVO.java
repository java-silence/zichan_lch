package com.itycu.server.dto;

import com.itycu.server.model.CgDingdan;
import com.itycu.server.model.CgDingdans;

import java.util.List;

/**
 * Created by fanlinglong on 2019/4/10.
 */
public class CgdingdanVO extends CgDingdan{

    private List<CgDingdans> cgDingdansList;

    public List<CgDingdans> getCgDingdansList() {
        return cgDingdansList;
    }

    public void setCgDingdansList(List<CgDingdans> cgDingdansList) {
        this.cgDingdansList = cgDingdansList;
    }



}
