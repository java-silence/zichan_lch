package com.itycu.server.app.service.impl;

import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.vo.XunJianVO;
import com.itycu.server.dao.ZcInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class XunjianServiceImpl implements XunJianService {


    @Autowired
    ZcInfoDao zcInfoDao;

    @Override
    public List<XunJianVO> getXunjianList() {
        Map<String,Object> map = new HashMap<>();
        List<XunJianVO>  xunJianVOList  = zcInfoDao.queryZcXunJianList(map);
        return null;
    }
}
