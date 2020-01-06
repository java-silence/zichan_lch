package com.itycu.server.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.itycu.server.dao.*;
import com.itycu.server.model.Dept;
import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.Todo;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.model.ZcBfCwitem;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcBfCwitems")
public class ZcBfCwitemController {

    public static final String bfActionUrl = "zcbf/auditZcBf.html";
    public static final String cwbfActionUrl = "zcbf/cwauditZcBf.html";

    @Autowired
    private ZcBfCwitemDao zcBfCwitemDao;

    @Autowired
    private ZcBfDao zcBfDao;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private ZcBfItemDao zcBfItemDao;

    @Autowired
    private FlowTodoItemDao flowTodoItemDao;

    @Autowired
    private DeptDao deptDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcBfCwitem save(@RequestBody ZcBfCwitem zcBfCwitem) {
        zcBfCwitemDao.save(zcBfCwitem);
        return zcBfCwitem;
    }

    /**
     * 查询审核信息
     * @param zcbfCwItemId   财务审核ID
     * @param todoId   待办ID
     * @return
     */
    @GetMapping("/checkList")
    @ApiOperation(value = "根据id获取")
    public Map checkList(@RequestParam(value = "zcbfCwItemId",required = false) Long zcbfCwItemId,
                         @RequestParam(value = "todoId",required = false) Long todoId ) {
        Map map = new HashMap<>();
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept currentDept = deptDao.getById(deptid);
        Dept pdept = deptDao.getById(currentDept.getPid());
        ZcBfCwitem zcBfCwitem = zcBfCwitemDao.getById(zcbfCwItemId);
        Todo todo = todoDao.getById(todoId);
        String todoIds = todo.getTodoIds();
        String[] array = todoIds.split(",");
        List<String> ids = Arrays.asList(array);
        // 查询申请信息
        List<Map<String,Object>> shenqing = zcBfItemDao.listShenqing(zcbfCwItemId);
        List<Map<String,Object>> shenpi = todoDao.getShenpi(ids);
        List<Map<String,Object>> shenhe = todoDao.getShenhe(todoId);
        map.put("zcBfCwitem",zcBfCwitem);
        map.put("shenqing",shenqing);
        map.put("shenpi",shenpi);
        map.put("shenhe",shenhe);
        map.put("pdeptName",pdept.getDeptname());
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 查询审核信息
     * @param todoId   待办ID
     * @return
     */
    @GetMapping("/preCheckList")
    @ApiOperation(value = "根据id获取")
    public Map preCheckList(@RequestParam(value = "todoId",required = false) Long todoId ) {
        Map map = new HashMap<>();
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept currentDept = deptDao.getById(deptid);
        Dept pdept = deptDao.getById(currentDept.getPid());
        Todo todo = todoDao.getById(todoId);
        HashMap<String, Object> params = new HashMap<>();
        params.put("flow_todo_id",todoId);
        List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(todoId);
        List<Long> bfitemIds = flowTodoItems.stream().map(e -> e.getFlowItemId()).collect(Collectors.toList());
        String todoIds = todo.getTodoIds();
        String[] array = todoIds.split(",");
        List<String> ids = Arrays.asList(array);
        // 查询申请信息
        List<Map<String,Object>> shenqing = zcBfItemDao.listShenqingByItemId(bfitemIds);
        List<Map<String,Object>> shenpi = todoDao.getShenpi(ids);
        List<Map<String,Object>> shenhe = todoDao.getShenhe(todoId);
        map.put("shenqing",shenqing);
        map.put("shenpi",shenpi);
        map.put("shenhe",shenhe);
        map.put("pdeptName",pdept.getDeptname());
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Map get(@PathVariable Long id) {
        Map map = new HashMap<>();
        ZcBfCwitem zcBfCwitem = zcBfCwitemDao.getById(id);
        Long bfId = zcBfCwitem.getBfId();

        Map<String, Object> shenqing = new HashMap<>();
        HashMap<String, Object> bf = zcBfDao.getZcBfDetail(bfId);
        shenqing.put("updateTime",bf.get("createTime"));
        shenqing.put("username",bf.get("username"));
        shenqing.put("nickname",bf.get("nickname"));
        shenqing.put("deptname",bf.get("deptname"));
        List<Map<String,Object>> shenpi = todoDao.findShenheList(bfId,bfActionUrl,"1");
        List<Map<String,Object>> shenhe = todoDao.findShenheList(bfId,cwbfActionUrl,"2");
        map.put("zcBfCwitem",zcBfCwitem);
        map.put("shenqing",shenqing);
        map.put("shenpi",shenpi);
        map.put("shenhe",shenhe);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcBfCwitem update(@RequestBody ZcBfCwitem zcBfCwitem) {
        zcBfCwitemDao.update(zcBfCwitem);
        return zcBfCwitem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcBfCwitem audit(@PathVariable Long id) {
        ZcBfCwitem zcBfCwitem = zcBfCwitemDao.getById(id);
        zcBfCwitemDao.update(zcBfCwitem);
        return zcBfCwitem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcBfCwitem unaudit(@PathVariable Long id) {
        ZcBfCwitem zcBfCwitem = zcBfCwitemDao.getById(id);
        zcBfCwitemDao.update(zcBfCwitem);
        return zcBfCwitem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcBfCwitemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcBfCwitem> list(PageTableRequest request) {
                return zcBfCwitemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = zcBfCwitemDao.count(request.getParams());

            List list = zcBfCwitemDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcBfCwitemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcBfCwitem> listAll() {
        List<ZcBfCwitem> list = zcBfCwitemDao.listAll();
        return list;
    }

}
