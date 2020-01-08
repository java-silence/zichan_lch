package com.itycu.server.app.service.impl;

import com.itycu.server.app.constant.SystemConstant;
import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.vo.xunjian.XunJianVO;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dao.ZcInspectRecordDao;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcInspectRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class XunjianServiceImpl implements XunJianService {


    private static Logger logger = LoggerFactory.getLogger(XunjianServiceImpl.class);

    @Autowired
    ZcInfoDao zcInfoDao;

    @Autowired
    ZcInspectRecordDao zcInspectRecordDao;


    @Override
    public List<XunJianVO> getXunjianList(SysUser sysUser) {
        if (SystemConstant.BWB.equals(sysUser.getC03()) || SystemConstant.ZHB.equals(sysUser.getC03()) ||
                SystemConstant.YYB.equals(sysUser.getC03()) ||
                SystemConstant.KJB.equals(sysUser.getC03())) {
            Map<String, Object> map = new HashMap<>();
            map.put("deptid", sysUser.getDeptid());
            List<XunJianVO> xunJianVOList = zcInfoDao.queryZcXunJianList(map);
            return xunJianVOList;
        } else {
            logger.info("当前用户的身份不是管理部门,部门id===={}", sysUser.getDeptid());
            return null;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertInspectRecord(ZcInspectRecord zcInspectRecord) {
        int flag = 0;
        int result = zcInspectRecordDao.insertInspectRecord(zcInspectRecord);
        if (result > 0) {
            flag = zcInspectRecordDao.updateZcInfoInspected(zcInspectRecord.getZcId());
        }
        return flag;
    }
}
