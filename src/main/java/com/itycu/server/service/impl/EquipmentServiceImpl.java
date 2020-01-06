package com.itycu.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.itycu.server.dao.*;
import com.itycu.server.dto.EquipmentVO;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.*;
import com.itycu.server.service.EquipmentService;
import com.itycu.server.service.FileService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fanlinglong on 2018/12/27.
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private TodoService todoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public Equipment save(Equipment equipment) {
        equipment.setCreateby(UserUtil.getLoginUser().getId());
//        equipment.setStatus("0");
//        zxBorrow.setDeptid(UserUtil.getLoginUser().getDeptid());
//        equipment.setFlowid(3L);
//        equipment.setStepid(2L);
        if(equipment.getStatus().equals("10")){
            if(UserUtil.getLoginUser().getDeptid()==11){
                equipment.setFlowid(13L);      //中心库流程
            }else{
                equipment.setFlowid(7L);      //各站库流程
            }
        }
        equipmentDao.save(equipment);

        if(equipment.getStatus().equals("10")){
            TodoDto todo = new TodoDto();
            todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() + "-" + equipment.getCname()+ "】设备入库申请");
            todo.setSendby(equipment.getCreateby());
            todo.setBizid(equipment.getId());
            todo.setFlowid(equipment.getFlowid());
            todo.setStepid(1L);
            todo.setStatus("0");
            todo.setBizcreateby(equipment.getCreateby());
            todo.setBizdeptid(equipment.getDeptid());
            todo.setBiztable("zx_equipment");

            todoService.sendTodo(todo);
        }


        log.debug("新增设备{}", equipment.getCname());
        return equipment;
    }

    @Override
    public TodoDto todo(TodoDto todoDto) {
        todoService.update(todoDto);
        if(todoDto.getStepid()==2 ){
            Equipment equipment = equipmentDao.getById(todoDto.getBizid());
            equipment.setStatus("11");
            equipmentDao.update(equipment);
        }
        return todoDto;
    }

    @Override
    public Map eqpImport(MultipartFile file) {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<EquipmentVO> list = null;
        FileInfo fileInfo = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), EquipmentVO.class, params);
            if(! CollectionUtils.isEmpty(list)){

                LoginUser loginUser = UserUtil.getLoginUser();
                Dept dept = deptDao.getById(loginUser.getDeptid());  //部门

                for (int i=0;i<list.size();i++){
                    EquipmentVO equipmentVO = list.get(i);
                    //判断必需的数据是否为空
                    if (equipmentVO.getSerialno() == null || "".equals(equipmentVO.getSerialno())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行序列号不能为空");
                        return map;
                    }

                    if (equipmentVO.getCname() == null || "".equals(equipmentVO.getCname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行设备名称不能为空");
                        return map;
                    }

                    if (equipmentVO.getXtfl() == null || "".equals(equipmentVO.getXtfl())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行设备分类不能为空");
                        return map;
                    }

                    if (equipmentVO.getSblb() == null || "".equals(equipmentVO.getSblb())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行设备类别不能为空");
                        return map;
                    }

                    if (equipmentVO.getXjqyname() == null || "".equals(equipmentVO.getXjqyname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行配属不能为空");
                        return map;
                    }

                    if (equipmentVO.getDeptname() == null || "".equals(equipmentVO.getDeptname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行所属站不能为空");
                        return map;
                    }


                    equipmentVO.setStatus("20");
                    equipmentVO.setDeptid(loginUser.getDeptid());
                    equipmentVO.setCreateby(loginUser.getId());
                }

                equipmentDao.saves(list);
                map.put("code","0");
//                fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    @Override
    public Map beipinImport(MultipartFile file) {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<EquipmentVO> list = null;
        FileInfo fileInfo = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), EquipmentVO.class, params);
            if(! CollectionUtils.isEmpty(list)){

                LoginUser loginUser = UserUtil.getLoginUser();
                Dept dept = deptDao.getById(loginUser.getDeptid());  //部门
                Warehouse warehouse = warehouseDao.getByDeptId(dept.getId());  //仓库
                List<EquipmentVO> equipmentVOList = new ArrayList<>();

                for (int i=0;i<list.size();i++){
                    EquipmentVO equipmentVO = list.get(i);
                    //判断必需的数据是否为空
                    if (equipmentVO.getCname() == null || "".equals(equipmentVO.getCname())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行设备名称不能为空");
                        return map;
                    }

                    if (equipmentVO.getC01() == null || "".equals(equipmentVO.getC01())){
                        map.put("code","1");
                        map.put("msg","第"+(i+2)+"行数量不能为空");
                        return map;
                    }

                    for (int a=0;a<Integer.valueOf(equipmentVO.getC01());a++){
                          EquipmentVO eqp = new EquipmentVO();


                          eqp.setStatus("11");
                          eqp.setDeptid(loginUser.getDeptid());
                          eqp.setCreateby(loginUser.getId());
                          eqp.setWhid(warehouse.getId());
                          eqp.setCname(equipmentVO.getCname());
                          eqp.setEtype(equipmentVO.getEtype());
                          eqp.setMemo(equipmentVO.getMemo());
                          eqp.setPrice(equipmentVO.getPrice());
                          eqp.setPinpai(equipmentVO.getPinpai());

                          equipmentVOList.add(eqp);
                    }


                }

                equipmentDao.saves(equipmentVOList);
                map.put("code","0");
//                fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }

}
