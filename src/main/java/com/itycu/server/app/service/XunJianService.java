package com.itycu.server.app.service;

import com.itycu.server.app.vo.XunJianVO;

import java.util.List;

public interface XunJianService {


    /**
     * 获取巡检的列表数据
     * @return
     */
    List<XunJianVO> getXunjianList();

}
