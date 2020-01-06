package com.itycu.server.service.impl;

import com.itycu.server.dao.ZcInspectDao;
import com.itycu.server.dao.ZcInspectRecordDao;
import com.itycu.server.dto.ZcInspectDto;
import com.itycu.server.dto.ZcInspectRecordDto;
import com.itycu.server.model.ZcInspect;
import com.itycu.server.model.ZcInspectRecord;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcInspectService;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZcInspectServiceImpl implements ZcInspectService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcInspectDao zcInspectDao;
    @Autowired
    private ZcInspectRecordDao zcInspectRecordDao;

    @Override
    public ZcInspectDto save(ZcInspectDto zcInspect) {
        zcInspect.setCreateBy(UserUtil.getLoginUser().getId());
        zcInspect.setDel(0);
        zcInspectDao.save(zcInspect);

        List<ZcInspectRecord> zcInspectRecordList = zcInspect.getZcInspectRecordList();
        if (!CollectionUtils.isEmpty(zcInspectRecordList)){
            zcInspectRecordDao.saves(zcInspectRecordList,zcInspect.getId());
        }
//        log.info("新增巡检任务{}", zcInspect.getCreateBy() + zcInspect.getZcId());
        return zcInspect;
    }

    @Override
    public ZcInspectDto update(ZcInspectDto zcInspect) {
        zcInspect.setUpdateBy(UserUtil.getLoginUser().getId());
        zcInspectDao.update(zcInspect);

        List<ZcInspectRecord> zcInspectRecordList = zcInspect.getZcInspectRecordList();
        if (!CollectionUtils.isEmpty(zcInspectRecordList)){
            zcInspectRecordDao.deleteByZcInReId(zcInspect.getId());
            zcInspectRecordDao.saves(zcInspectRecordList,zcInspect.getId());
        }
//        log.info("编辑巡检任务{}", zcInspect.getCreateBy() + zcInspect.getZcId());
        return zcInspect;
    }

    @Override
    public void delete(Long id) {
        ZcInspect zcInspect = new ZcInspect();
        zcInspect.setId(id);
        zcInspect.setUpdateBy(UserUtil.getLoginUser().getId());
        zcInspect.setDel(1);
        zcInspectDao.update(zcInspect);
        log.info("删除巡检任务{}", zcInspect.getUpdateBy());
    }

    @Override
    public ZcInspectDto zcInspectTodo(ZcInspectDto zcInspect) {
        List<ZcInspectRecord> zcInspectRecords = zcInspect.getZcInspectRecordList();
        if (!CollectionUtils.isEmpty(zcInspectRecords)){

        }
        log.info("巡检异常待办{}", zcInspect.getUpdateBy());
        return zcInspect;
    }

    @Override
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<ZcInspectRecordDto> zcInspectRecordDtos = zcInspectRecordDao.listByCondition(request.getParams());
        String fileName = "巡检记录";
//        if (!CollectionUtils.isEmpty(equipmentList)) {
        String[] headers = new String[]{"资产追溯码","资产名称","资产编码","卡片编号","管理部门","使用部门","存放地点","巡检周期","巡检结果","异常处理结果",
                "巡检部门","巡检人"};

        List<Object[]> datas = new ArrayList<>(zcInspectRecordDtos.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZcInspectRecordDto zcInspectRecordDto : zcInspectRecordDtos) {
            Object[] objects = new Object[]{zcInspectRecordDto.getEpcid(),zcInspectRecordDto.getZcName(),zcInspectRecordDto.getZcCodenum(),zcInspectRecordDto.getCardNum(),
                    zcInspectRecordDto.getGlDeptName(),zcInspectRecordDto.getSyDeptName(),zcInspectRecordDto.getStoreAddress()
                    ,zcInspectRecordDto.getInspectTime(),zcInspectRecordDto.getResult(),zcInspectRecordDto.getBz(),zcInspectRecordDto.getGlDeptName(),
                    zcInspectRecordDto.getCheckUsername()
            };
            datas.add(objects);
        }

        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
//        }
        log.debug("巡检记录导出{}", UserUtil.getLoginUser().getId());
    }
}
