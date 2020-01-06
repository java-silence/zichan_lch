package com.itycu.server.controller;

import com.itycu.server.dao.EquipmentDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dto.EquipmentVO;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Equipment;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.EquipmentService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Equipment save(@RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
//        equipment.setCreateby(UserUtil.getLoginUser().getId());
//        equipmentDao.save(equipment);
//        return equipment;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public EquipmentVO get(@PathVariable Long id) {
        return equipmentDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Equipment update(@RequestBody Equipment equipment) {
        equipment.setUpdateby(UserUtil.getLoginUser().getId());
        equipmentDao.update(equipment);
        return equipment;
    }

    @PutMapping("/todo")
    @ApiOperation(value = "修改")
    @Transactional
    public TodoDto todo(@RequestBody TodoDto todoDto) {
        return equipmentService.todo(todoDto);
    }
    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Equipment audit(@PathVariable Long id) {
        Equipment equipment = equipmentDao.getById(id);

        equipment.setAuditby(UserUtil.getLoginUser().getId());
        equipment.setAuditTime(new Date());
        equipment.setStatus("1");
        equipmentDao.update(equipment);
        return equipment;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Equipment unaudit(@PathVariable Long id) {
        Equipment equipment = equipmentDao.getById(id);

        equipment.setAuditby(null);
        equipment.setAuditTime(null);
        equipment.setStatus("0");
        equipmentDao.update(equipment);
        return equipment;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return equipmentDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<EquipmentVO> list(PageTableRequest request) {
                return equipmentDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }


    @GetMapping("/list2")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:eqp:queryall") == 0){
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = equipmentDao.count(request.getParams());

        List list = equipmentDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        equipmentDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Equipment> listAll() {
        List<Equipment> list = equipmentDao.listAll();
        return list;
    }

    @GetMapping("/listadd")
    @ApiOperation(value = "列出未安装数据")
    public List<Equipment> listAdd() {
        List<Equipment> list = equipmentDao.listAdd();
        return list;
    }

    @GetMapping("/listoldadd")
    @ApiOperation(value = "列出已安装数据")
    public List<Equipment> listOldadd() {
        List<Equipment> list = equipmentDao.listOldadd();
        return list;
    }


    @GetMapping("/listxjsb/{id}")
    @ApiOperation(value = "列出巡检设备")
    public List<Equipment> listxjsb(@PathVariable Long id) {
        List<Equipment> list = equipmentDao.listxjsb(id);
        return list;
    }

    @GetMapping("/listnoxjsb/{id}")
    @ApiOperation(value = "列出未包含的巡检设备")
    public List<Equipment> listnoxjsb(@PathVariable Long id) {
        List<Equipment> list = equipmentDao.listnoxjsb(id);
        return list;
    }

    @GetMapping("/listbywhid/{whid}")
    @ApiOperation(value = "列出未安装数据")
    public List<Equipment> listbywhid(@PathVariable Long whid) {
        List<Equipment> list = equipmentDao.listbywhid(whid);
        return list;
    }
    @GetMapping("/listbydeptid/{deptid}")
    @ApiOperation(value = "列出该部门所有设备")
    public List<Equipment> listbydeptid(@PathVariable Long deptid) {
        List<Equipment> list = equipmentDao.listbydept(deptid);
        return list;
    }

    @GetMapping("/listlogindept/")
    @ApiOperation(value = "列出登录用户部门所有设备")
    public List<Equipment> listlogindept() {
        List<Equipment> list = equipmentDao.listbydept(UserUtil.getLoginUser().getDeptid());
        return list;
    }

    @GetMapping("/listbylbid/{lbid}")
    @ApiOperation(value = "列出指定设备类型设备")
    public List<Equipment> listbylbid(@PathVariable Long lbid) {
        LoginUser loginUser = UserUtil.getLoginUser();
        List<Equipment> list = equipmentDao.listbywlbid(lbid,loginUser.getDeptid());
        return list;
    }

    @GetMapping("/listbystatus/{status}")
    @ApiOperation(value = "列出本站指定状态设备")
    public List<Equipment> listbystatus(@PathVariable String status) {
        LoginUser loginUser = UserUtil.getLoginUser();
        List<Equipment> list = equipmentDao.listbystatus(status,loginUser.getDeptid());
        return list;
    }

    @PostMapping("/isxunsaves/{id}")
    @ApiOperation(value = "保存")
    public String addsblist(@PathVariable Long id, @RequestBody List<Long> sbids) {
        if (sbids != null) {
            //xunjianjihuaDao.deletebyxjqy(id);
            if (!CollectionUtils.isEmpty(sbids)) {
                equipmentDao.clearisxun(id);
                equipmentDao.isxunsaves(id, sbids);
            }
        }
        return "1";
    }


    @GetMapping("/listByCondition")
    @ApiOperation(value = "根据条件列出设备")
    public List<EquipmentVO> listByCondition(PageTableRequest request) {
        List<EquipmentVO> list = equipmentDao.listByCondition(request.getParams());
        return list;
    }

    @PostMapping("/eqpImport")
    @ApiOperation(value = "设备导入")
    @Transactional
    public Map eqpImport(MultipartFile file) throws IOException {
         return equipmentService.eqpImport(file);
    }

    @PostMapping("/beipinImport")
    @ApiOperation(value = "备品导入")
    @Transactional
    public Map beipinImport(MultipartFile file) throws IOException {
        return equipmentService.beipinImport(file);
    }
}
