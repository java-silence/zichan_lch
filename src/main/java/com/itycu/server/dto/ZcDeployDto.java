package com.itycu.server.dto;

import com.itycu.server.model.ZcDeploy;
import com.itycu.server.model.ZcDeployItem;

import java.util.List;

/**
 * 资产调配表单
 * @author lch
 * @create 2019-12-04 15:55
 */
public class ZcDeployDto extends ZcDeploy {

    private List<ZcDeployItem> zcDeployItems;

    public List<ZcDeployItem> getZcDeployItems() {
        return zcDeployItems;
    }

    public void setZcDeployItems(List<ZcDeployItem> zcDeployItems) {
        this.zcDeployItems = zcDeployItems;
    }
}
