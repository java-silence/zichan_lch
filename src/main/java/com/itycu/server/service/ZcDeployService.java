package com.itycu.server.service;

import com.itycu.server.dto.ZcDeployCheckDto;
import com.itycu.server.dto.ZcDeployDto;

/**
 * 资产调度
 * @author lch
 * @create 2019-12-03 21:05
 */
public interface ZcDeployService {

    /**
     * 保存流程
     * @param zcDeployDto
     * @return
     */
    ZcDeployDto save(ZcDeployDto zcDeployDto);

    /**
     * 调配启动流程
     * @param deployId
     */
    void startProcess(String deployId);

    /**
     * 资产审核
     * @param zcDeployCheckDto
     */
    String check(ZcDeployCheckDto zcDeployCheckDto);

    /**
     * 修改调配单
     * @param zcDeployDto
     */
    void updateZcDeploy(ZcDeployDto zcDeployDto);

}
