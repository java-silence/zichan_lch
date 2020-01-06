package com.itycu.server.service;

import com.itycu.server.dto.ZcCheckDto;
import com.itycu.server.model.ZcCheck;

import java.util.Map;

public interface ZcCheckService {
    /**
     * 资产盘点单
     *
     * @param zcCheckDto
     * @return
     */
    void saves(ZcCheck zcCheckDto);


    /**
     * 插入盘点数据
     *
     * @param zcCheck 盘点数据
     * @return
     */
    int insertZcTask(ZcCheck zcCheck);



   int  checkHasCreatedCount(long createBy,long deptId);


    Map pdeSaveCheck(String deptID,int profit);
}
