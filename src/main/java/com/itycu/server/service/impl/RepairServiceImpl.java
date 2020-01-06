package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Equipment;
import com.itycu.server.model.Repairs;
import com.itycu.server.dto.RepairVO;
import com.itycu.server.model.Repair;
import com.itycu.server.model.Todo;
import com.itycu.server.service.RepairService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/8/11.
 */
@Service
public class RepairServiceImpl implements RepairService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private RepairDao repairDao;

    @Autowired
    private RepairsDao repairsDao;


    @Autowired
    private TodoService todoService;

    @Autowired
    private FlowmemberDao flowmemberDao;

    @Autowired
    private EquipmentDao equipmentDao;


    @Override
    public Repair save(RepairVO repairVO) {
        Repair repair = (Repair)repairVO;
        repair.setCreateby(UserUtil.getLoginUser().getId());
        repair.setDeptid(UserUtil.getLoginUser().getDeptid());
        if (repairVO.getSend() == 1){
            repair.setAuditby(UserUtil.getLoginUser().getId());
            repair.setAuditTime(new Date());
            repair.setStatus("1");
//            repair.setBiztype( "1" );
            repair.setFlowid(new Long(repair.getBiztype()));
            repair.setStepid(2L);
        }

        List<Repairs> repairsList = repairVO.getRepairsList();

        if(repairsList!=null){
            if (repairsList.size()==0){
                 repair.setMaterial("1");
            }
        }else{
            repair.setMaterial("1");
        }

        repairDao.save(repair);


        if(repairsList!=null){
            if (repairsList.size()!=0){
                repairsDao.saves(repairsList,repair.getId());
            }
        }

        if (repairVO.getSend() == 1){
            sendTodo(repairVO);
        }

        saveFiles(repair.getId(),repair.getBiztype(),repairVO.getFileIds());

        Equipment equipment = equipmentDao.getById(repair.getEqpid());
        equipment.setStatus("报修");
        equipmentDao.update(equipment);

        log.debug("新增维修单{}", repair.getDescpic());
        return repair;
    }

    @Override
    public Repair update(RepairVO repairVO) {
        Repair repair = repairVO;

        List<Repairs> repairsList = repairVO.getRepairsList();
        if (repairsList.size()!=0){
            repairsDao.deletelist(repair.getId());
            repairsDao.saves(repairsList,repair.getId());
        }
        repair.setUpdateby(UserUtil.getLoginUser().getId());

        if (repairVO.getSend() == 1){
            repair.setAuditby(UserUtil.getLoginUser().getId());
            repair.setAuditTime(new Date());
            repair.setStatus("1");
            repair.setFlowid(new Long(repair.getBiztype()));
            repair.setStepid(2L);
        }

        repairDao.update(repair);

        if (repairVO.getSend() == 1){
            sendTodo(repairVO);
        }
//        repairDao.update(repair);

        saveFiles(repair.getId(),repair.getBiztype(),repairVO.getFileIds());

        log.debug("更新维修单{}", repair.getDescpic());
        return repair;
    }

    public void sendTodo(RepairVO repair){
        repair = repairDao.getById(repair.getId());
        TodoDto todo = new TodoDto();
        todo.setAuditby(flowmemberDao.getMemidByFlowStep(repair.getFlowid(),repair.getStepid()) );
        todo.setBiaoti("【" + repair.getCreatename() +"】" + repair.getCname() + "维修单审核");
        todo.setSendby(repair.getCreateby());
        todo.setBizid(repair.getId());
        todo.setFlowid(repair.getFlowid());
        todo.setStepid(1L);
        todo.setStatus("0");
        todo.setBizcreateby(repair.getCreateby());
        todo.setBizdeptid(repair.getDeptid());
        todo.setBiztable("zx_repair");

        todoService.sendTodo(todo);
    }
    private void saveFiles(long repairId,String biztype,List<String> fileIds){
        if(fileIds != null && fileIds.size()>0){
            repairDao.saveFiles(repairId,biztype,fileIds );
        }
    }

}


