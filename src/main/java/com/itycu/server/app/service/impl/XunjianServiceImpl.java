package com.itycu.server.app.service.impl;

import com.itycu.server.app.constant.SystemConstant;
import com.itycu.server.app.dto.xunjian.XunJianSubmitDTO;
import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.vo.xunjian.XunJianVO;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dao.ZcInspectDao;
import com.itycu.server.dao.ZcInspectRecordDao;
import com.itycu.server.model.*;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    public List<XunJianVO> getXunjianList(SysUser sysUser) {
        if (SystemConstant.BWB.equals(sysUser.getC03()) || SystemConstant.ZHB.equals(sysUser.getC03()) ||
                SystemConstant.YYB.equals(sysUser.getC03()) ||
                SystemConstant.KJB.equals(sysUser.getC03())) {
            Map<String, Object> map = new HashMap<>();
            map.put("deptid", sysUser.getDeptid());
            List<XunJianVO> xunJianVOList = zcInfoDao.queryZcXunJianList(map);
            if (!CollectionUtils.isEmpty(xunJianVOList)) {
                xunJianVOList.forEach(k -> {
                    k.setStatus(0);
                });
            }
            return xunJianVOList;
        } else {
            logger.info("当前用户的身份不是管理部门,部门id===={}", sysUser.getDeptid());
            return null;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertInspectRecord(XunJianSubmitDTO xunJianSubmitDTO) {
        ZcInspectRecord zcInspectRecord = new ZcInspectRecord();
        zcInspectRecord.setImg(xunJianSubmitDTO.getImg());
        zcInspectRecord.setOpinion(xunJianSubmitDTO.getOpinion());
        zcInspectRecord.setAppearance(xunJianSubmitDTO.getAppearance());
        zcInspectRecord.setResult(xunJianSubmitDTO.getResult());
        zcInspectRecord.setFunct(xunJianSubmitDTO.getFunct());
        zcInspectRecord.setResult(xunJianSubmitDTO.getResult());


        int zcInspectRelId = 0;



        int flag = 0;
        int result = zcInspectRecordDao.insertInspectRecord(zcInspectRecord);
        if (result > 0) {
            //更新巡检的天数为null
            flag = zcInfoDao.updateInspectStatus(zcInspectRecord.getZcInspectId());
        }
        return flag;
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
