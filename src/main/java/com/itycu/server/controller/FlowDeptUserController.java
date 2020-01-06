package com.itycu.server.controller;

import com.itycu.server.dao.FlowDao;
import com.itycu.server.dao.FlowDeptUserDao;
import com.itycu.server.dao.FlowstepDao;
import com.itycu.server.dto.FlowDeptUserDTO;
import com.itycu.server.model.Flow;
import com.itycu.server.model.FlowDeptUser;
import com.itycu.server.model.Flowstep;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 节点部门用户
 */
@RestController
@RequestMapping("/flowDeptUsers")
public class FlowDeptUserController {

    @Autowired
    private FlowDeptUserDao flowDeptUserDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private FlowstepDao flowstepDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public FlowDeptUser save(@RequestBody FlowDeptUser flowDeptUser) {
        flowDeptUser.setDel(0);
        flowDeptUserDao.save(flowDeptUser);
        return flowDeptUser;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public FlowDeptUser get(@PathVariable Long id) {
        return flowDeptUserDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public FlowDeptUser update(@RequestBody FlowDeptUser flowDeptUser) {
        flowDeptUserDao.update(flowDeptUser);
        return flowDeptUser;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public FlowDeptUser audit(@PathVariable Long id) {
        FlowDeptUser flowDeptUser = flowDeptUserDao.getById(id);
        flowDeptUserDao.update(flowDeptUser);
        return flowDeptUser;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public FlowDeptUser unaudit(@PathVariable Long id) {
        FlowDeptUser flowDeptUser = flowDeptUserDao.getById(id);
        flowDeptUserDao.update(flowDeptUser);
        return flowDeptUser;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowDeptUserDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<FlowDeptUser> list(PageTableRequest request) {
                return flowDeptUserDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {


            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = flowDeptUserDao.count(request.getParams());

            List list = flowDeptUserDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowDeptUserDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<FlowDeptUser> listAll() {
        List<FlowDeptUser> list = flowDeptUserDao.listAll();
        return list;
    }

    @GetMapping("/listAllFlow")
    @ApiOperation(value = "列出所有流程及节点")
    public Map listAllFlow () {

        Map map = new HashMap<>();
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        List<Flow> flows = flowDao.listAll();
        for (Flow flow : flows) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("id",String.valueOf(flow.getId()));
            map1.put("name",flow.getFlowname());
            map1.put("status","0");

            // 流程节点
            ArrayList<Map<String,Object>> list2 = new ArrayList<>();
            List<Flowstep> flowsteps = flowstepDao.listSteps(flow.getId());
            for (Flowstep flowstep : flowsteps) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("id",flowstep.getId());
                map2.put("name",flowstep.getStepname());
                map2.put("status","1");
                list2.add(map2);
            }

            map1.put("children",list2);
            list.add(map1);
        }

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    /**
     * 部门用户列表
     * @param request
     * @return
     */
    @GetMapping("/flowDeptList")
    @ApiOperation(value = "部门用户列表")
    //@PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map flowDeptList(PageTableRequest request) {

//        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:user:querydept") > 0){
//            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
//        }

        Map map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        Map<String, Object> params = request.getParams();
        int count = flowDeptUserDao.count(params);
        List list = new ArrayList<>();
        if (params.get("flowstep_id")!=null){
            list = flowDeptUserDao.listFloeDeptUser(request.getParams(), page*limit-limit, limit);
        }
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    /**
     * 根据ID获取部门用户信息
     * @return
     */
    @GetMapping("/flowDeptUser/{id}")
    public Map getFlowDeptUser(@PathVariable("id")String id){
        return flowDeptUserDao.getFlowDeptUser(id);
    }

    /**
     * 根据ID获取部门用户信息
     * @return
     */
    @PostMapping("/updateFlowDeptUser")
    public Map updateFlowDeptUser(@RequestParam(value = "id",required = false)Long id,
                                  @RequestParam(value = "userid",required = false)Integer userid,
                                  @RequestParam(value = "deptid",required = false)Integer deptid,
                                  @RequestParam(value = "flowstep_id",required = false)Long flowstep_id,
                                  @RequestParam(value = "memo",required = false)String bz){
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(id)){
            FlowDeptUser flowDeptUser = new FlowDeptUser();
            Flowstep flowstep = flowstepDao.getById(flowstep_id);
            flowDeptUser.setFlowId(flowstep.getFlowid().intValue());
            flowDeptUser.setFlowstepId(flowstep_id.intValue());
            flowDeptUser.setUserId(userid);
            flowDeptUser.setDeptId(deptid);
            flowDeptUser.setBz(bz);
            flowDeptUserDao.save(flowDeptUser);
            map.put("code","0");
            map.put("msg","保存成功");
        }else {
            FlowDeptUser flowDeptUser = flowDeptUserDao.getById(id);
            flowDeptUser.setDeptId(deptid);
            flowDeptUser.setUserId(userid);
            flowDeptUser.setUpdateTime(new Date());
            if (!ObjectUtils.isEmpty(bz)){
                flowDeptUser.setBz(bz);
            }
            flowDeptUserDao.update(flowDeptUser);
            map.put("code","0");
            map.put("msg","修改成功");
        }
        return map;
    }

}
