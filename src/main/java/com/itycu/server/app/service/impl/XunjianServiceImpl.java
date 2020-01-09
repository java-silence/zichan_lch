package com.itycu.server.app.service.impl;

import com.itycu.server.app.constant.SystemConstant;
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
import org.springframework.util.StringUtils;

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
    public int insertInspectRecord(ZcInspectRecord zcInspectRecord) {
        int flag = 0;
        ZcInfo zcInfo = zcInfoDao.getById(zcInspectRecord.getZcId());
        if (null == zcInfo) {
            logger.info("获取的资产数据目前存在");
            return flag;
        }
        //ZcInspect zcInspect = createZcInspect(zcInfo);
        /**
         * 设置巡检结果 看是否是完成 1完整 0 不完整
         */
          //zcInspect.setStatus(zcInspectRecord.getResult());
          //zcInspectDao.save(zcInspect);
        int result = zcInspectRecordDao.insertInspectRecord(zcInspectRecord);
        if (result > 0) {
            //更新巡检的天数为null
           // flag = zcInspectRecordDao.updateZcInfoInspected(zcInspectRecord.getZcId());
            flag = zcInfoDao.updateInspectStatus(zcInspectRecord.getZcInspectId());
        }
        return flag;
    }

    @Override
    public List<XunJianVO> getInspectRecordList(SysUser sysUser) {
        List<XunJianVO> list = new ArrayList<>();
        long deptId = sysUser.getDeptid();
        List<ZcInspect> zcInspectList = zcInspectDao.getByDeptId(deptId);
        if (!CollectionUtils.isEmpty(zcInspectList)) {
            zcInspectList.forEach(k -> {
                ZcInfo zcInfo = zcInfoDao.getById(k.getZcId());
                XunJianVO xunJianVO = new XunJianVO();
                xunJianVO.setStatus(1);
                xunJianVO.setCreateTime(k.getCreateTime());
                xunJianVO.setEpcid(zcInfo.getEpcid());
                xunJianVO.setGlDeptId(zcInfo.getGlDeptId());
                xunJianVO.setSyDeptId(zcInfo.getSyDeptId());
                xunJianVO.setGlDeptName(getDeptName(zcInfo.getGlDeptId()));
                xunJianVO.setSyDeptName(getDeptName(zcInfo.getGlDeptId()));
                xunJianVO.setZcCodenum(zcInfo.getZcCodenum());
                xunJianVO.setZcName(zcInfo.getZcName());
                xunJianVO.setInspectTime(k.getDays());
                xunJianVO.setResult(k.getStatus());
                list.add(xunJianVO);
            });
        }
        return list;
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
}
