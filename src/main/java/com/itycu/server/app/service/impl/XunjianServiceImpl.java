package com.itycu.server.app.service.impl;

import com.itycu.server.app.constant.SystemConstant;
import com.itycu.server.app.dto.xunjian.XunJianSubmitDTO;
import com.itycu.server.app.model.AppXunJianReal;
import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.xunjian.XunJianVO;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dao.ZcInspectDao;
import com.itycu.server.dao.ZcInspectRecordDao;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
public class XunjianServiceImpl implements XunJianService {


    private static Logger logger = LoggerFactory.getLogger(XunjianServiceImpl.class);

    @Autowired
    ZcInfoDao zcInfoDao;

    @Autowired
    ZcInspectRecordDao zcInspectRecordDao;

    @Autowired
    ZcInspectDao zcInspectDao;

    @Autowired
    DeptDao deptDao;


    @Override
    public List<ZcInfoDto> getXunjianList(SysUser sysUser) {
        if (SystemConstant.BWB.equals(sysUser.getC03()) || SystemConstant.ZHB.equals(sysUser.getC03()) ||
                SystemConstant.YYB.equals(sysUser.getC03()) ||
                SystemConstant.KJB.equals(sysUser.getC03())) {
            Map<String, Object> map = new HashMap<>();
            map.put("glDeptId", sysUser.getDeptid());
            map.put("daixunjian", "1");
            map.put("useStatus","1");
            map.put("del","0");
            List<ZcInfoDto> xunJianVOList = zcInfoDao.xunjianList(map);
            return xunJianVOList;
        } else {
            logger.info("当前用户的身份不是管理部门,部门id===={}", sysUser.getDeptid());
            return null;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> insertInspectRecord(XunJianSubmitDTO xunJianSubmitDTO, ZcInfo zcInfo) {
        Map<String, Object> map = new HashMap<>();
        try {
            int result = 0;
            ZcInspectRecord zcInspectRecord = new ZcInspectRecord();
            zcInspectRecord.setImg(xunJianSubmitDTO.getImg());
            zcInspectRecord.setOpinion(xunJianSubmitDTO.getOpinion());
            zcInspectRecord.setAppearance(xunJianSubmitDTO.getAppearance());
            zcInspectRecord.setResult(xunJianSubmitDTO.getResult());
            zcInspectRecord.setFunct(xunJianSubmitDTO.getFunct());
            zcInspectRecord.setResult(xunJianSubmitDTO.getResult());
            AppXunJianReal appXunJianReal = zcInspectDao.queryZcRealIdByZcId(zcInfo.getId());
            zcInspectRecord.setZcId(zcInfo.getId());
            zcInspectRecord.setCreateBy(UserUtil.getLoginUser().getId());
            zcInspectRecord.setBz("");
            zcInspectRecord.setUpdateTime(new Date());
            zcInspectRecord.setCreateTime(new Date());
            zcInspectRecord.setCheckTime(new Date());
            zcInspectRecord.setCheckUserId(UserUtil.getLoginUser().getId());

            if (null != appXunJianReal) {
                zcInspectRecord.setZcRealId(appXunJianReal.getId());
                if (0 != appXunJianReal.getZcInspectId()) {
                    zcInspectRecord.setZcInspectId(new Long(appXunJianReal.getId()));
                }
                if (appXunJianReal.getStatus() == 1) {
//                    map.put("code", 400);
//                    map.put("message", "该数据已经巡检过了");
//                    map.put("data", null);
//                    return map;

                    zcInspectDao.updateLastInspect(zcInspectRecord);
                } else {
                    result = zcInspectRecordDao.insertInspectRecord(zcInspectRecord);
                    if (result > 0) {
                        zcInfoDao.updateInspectStatus(appXunJianReal.getZcInspectId());
                    }
                }
            }
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", result);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map = FailMap.createFailMap();
            return map;
        }
    }

    @Override
    public List<XunJianVO> getInspectRecordList(SysUser sysUser) {
        long deptId = sysUser.getDeptid();
        List<XunJianVO> zcInspectList = zcInspectDao.getByDeptId(deptId);
        return zcInspectList;
    }


    private String getDeptName(long deptId) {
        Dept dept = deptDao.getById(deptId);
        return (null == dept ? "" : dept.getDeptname());
    }


    private ZcInspect createZcInspect(ZcInfo zcInfo) {
        ZcInspect zcInspect = new ZcInspect();
        zcInspect.setZcId(zcInfo.getId());
        zcInspect.setDays(zcInfo.getInspectTime() + "");
        zcInspect.setLastCheckTime(new Date());
        zcInspect.setDel(0);
        zcInspect.setBz(null);
        zcInspect.setCreateBy(UserUtil.getLoginUser().getId());
        zcInspect.setUpdateBy(null);
        zcInspect.setCreateTime(new Date());
        zcInspect.setUpdateTime(null);
        zcInspect.setCheckTime(new Date());
        zcInspect.setCheckUserId(UserUtil.getLoginUser().getId());
        zcInspect.setCheckUsername(UserUtil.getLoginUser().getUsername());
        zcInspect.setCheckDeptId(UserUtil.getLoginUser().getDeptid());
        zcInspect.setCheckDeptName(UserUtil.getLoginUser().getLoginUserDepartName());
        return zcInspect;
    }


    @Override
    public ZcInspectRecord getInspectRecordById(int id) {
        ZcInspectRecord zcInspectRecord = zcInspectRecordDao.getByInspectId(id);
        return zcInspectRecord;
    }
}
