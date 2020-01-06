package com.itycu.server.service.impl;

import com.itycu.server.dao.ZcChangeRecordDao;
import com.itycu.server.dto.ZcChangeRecordDto;
import com.itycu.server.model.ZcChangeRecord;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcChangeRecordService;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ZcChangeRecordServiceImpl implements ZcChangeRecordService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcChangeRecordDao zcChangeRecordDao;

    @Override
    public ZcChangeRecord save(ZcInfo zcInfo,String chageField) {
        ZcChangeRecord zcChangeRecord = new ZcChangeRecord();
        if (zcInfo != null){
            BeanUtils.copyProperties(zcInfo,zcChangeRecord);
            zcChangeRecord.setCreateBy(UserUtil.getLoginUser().getId());
            zcChangeRecord.setCreateTime(new Date());
            zcChangeRecord.setZcInfoId(zcInfo.getId());
            zcChangeRecord.setChangeField(chageField);
            zcChangeRecordDao.save(zcChangeRecord);
            log.info("保存变更记录{}");
        }
        return zcChangeRecord;
    }

    @Override
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<ZcChangeRecordDto> zcChangeRecordDtoList = zcChangeRecordDao.listByCondition(request.getParams());
        String fileName = "变更记录";
//        if (!CollectionUtils.isEmpty(equipmentList)) {
        String[] headers = new String[]{"资产编号","资产编码","资产名称","资产分类","规格","型号","单位","使用状态","管理部门","使用部门","使用人","存放地点","维护周期/天"
                ,"资产来源","原价值","累计折旧","本年折旧","净值","减值准备","净额","净残值率","净残值","开始使用日期","使用月限","已计提期数"
                ,"剩余期限","服务商名称","联系人","联系方式","服务商地址","保修期限","","创建人","创建时间","备注"};

        List<Object[]> datas = new ArrayList<>(zcChangeRecordDtoList.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZcChangeRecordDto zcChangeRecordDto : zcChangeRecordDtoList) {
            String StartUseTime = "";
            String createTime = "";
            if(zcChangeRecordDto.getStartUseTime() != null){
                StartUseTime = s.format(zcChangeRecordDto.getStartUseTime());
            }
            if(zcChangeRecordDto.getCreateTime() != null){
                createTime = s1.format(zcChangeRecordDto.getCreateTime());
            }
            Object[] objects = new Object[]{zcChangeRecordDto.getZcCodenum(),zcChangeRecordDto.getZcCoding(),zcChangeRecordDto.getZcName(),zcChangeRecordDto.getZcCategoryName(),
                    zcChangeRecordDto.getSpecification(),zcChangeRecordDto.getModel(),zcChangeRecordDto.getUnit(),zcChangeRecordDto.getUseStatusName(),
                    zcChangeRecordDto.getGlDeptName(),zcChangeRecordDto.getSyDeptName(),zcChangeRecordDto.getSyName(),zcChangeRecordDto.getStoreAddress()
                    ,zcChangeRecordDto.getMaintainCycle(),zcChangeRecordDto.getZcFrom(),zcChangeRecordDto.getOriginalValue(),zcChangeRecordDto.getLjZhejiu()
                    ,zcChangeRecordDto.getBnZhejiu(),zcChangeRecordDto.getNetvalue(),zcChangeRecordDto.getJzzb(),zcChangeRecordDto.getNet(),
                    zcChangeRecordDto.getNetResidualRate(),zcChangeRecordDto.getNetResidualValue(),StartUseTime,zcChangeRecordDto.getUseMonths()
                    ,zcChangeRecordDto.getHaveCount(),zcChangeRecordDto.getRemainingperiod(),zcChangeRecordDto.getCname(),zcChangeRecordDto.getVenperson()
                    ,zcChangeRecordDto.getVenphone(),zcChangeRecordDto.getVenaddress(),zcChangeRecordDto.getWarrantyperiod(),zcChangeRecordDto.getEpcid(),zcChangeRecordDto.getCreator()
                    ,createTime,zcChangeRecordDto.getBz()
            };
            datas.add(objects);
        }

        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
//        }
        log.debug("变更记录导出{}", UserUtil.getLoginUser().getId());
    }
}
