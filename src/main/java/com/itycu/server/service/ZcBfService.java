package com.itycu.server.service;

import com.itycu.server.dto.ZcBfCheckDto;
import com.itycu.server.dto.ZcBfDto;
import java.util.Map;

public interface ZcBfService {

    /**
     * 保存流程
     * @param zcBfDto
     * @return
     */
    Map save(ZcBfDto zcBfDto);

    /**
     * 报废启动流程
     * @param bfId
     */
    void startProcess(String bfId);

    /**
     * 资产审核
     * @param zcBfCheckDto
     */
    void check(ZcBfCheckDto zcBfCheckDto);

    void checkNew(ZcBfCheckDto zcBfCheckDto);

    /**
     * 修改报废单
     * @param zcBfDto
     */
    Map updateZcBf(ZcBfDto zcBfDto);

    /**
     * 审核部提交至财务部
     * @param zcBfCheckDto
     */
    void submitToCw(ZcBfCheckDto zcBfCheckDto);

    /**
     * 财务审核
     * @param zcBfCheckDto
     */
    Long cwcheck(ZcBfCheckDto zcBfCheckDto);
}
