package com.itycu.server.service;

import com.itycu.server.dto.ZcBuyCheckDto;
import com.itycu.server.dto.ZcBuyDto;

/**
 * 资产购买
 * @author lch
 * @create 2019-12-04 10:25
 */
public interface ZcBuyService {

    /**
     * 保存流程
     * @param zcBuyDto
     * @return
     */
    ZcBuyDto save(ZcBuyDto zcBuyDto);

    /**
     * 购买启动流程
     * @param buyId
     */
    void startProcess(String buyId);

    /**
     * 购买资产审核
     * @param zcBuyCheckDto
     */
    String check(ZcBuyCheckDto zcBuyCheckDto);

    /**
     * 修改购买单
     * @param zcBuyDto
     */
    void updateZcBuy(ZcBuyDto zcBuyDto);

}
