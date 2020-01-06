package com.itycu.server.dto;

import com.itycu.server.model.ZcBuy;
import com.itycu.server.model.ZcBuyItem;

import java.util.List;

/**
 * 资产购买表单
 * @author lch
 * @create 2019-12-04 15:56
 */
public class ZcBuyDto extends ZcBuy {

    private List<ZcBuyItem> zcBuyItemList;

    public List<ZcBuyItem> getZcBuyItemList() {
        return zcBuyItemList;
    }

    public void setZcBuyItemList(List<ZcBuyItem> zcBuyItemList) {
        this.zcBuyItemList = zcBuyItemList;
    }
}
