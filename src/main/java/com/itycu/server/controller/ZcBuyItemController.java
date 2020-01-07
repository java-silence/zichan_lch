package com.itycu.server.controller;

import java.util.*;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcBuyItemDao;
import com.itycu.server.model.ZcBuyItem;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zcBuyItems")
public class ZcBuyItemController {

    @Autowired
    private ZcBuyItemDao zcBuyItemDao;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcBuyItem save(@RequestBody ZcBuyItem zcBuyItem) {
        zcBuyItemDao.save(zcBuyItem);
        return zcBuyItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcBuyItem get(@PathVariable Long id) {
        return zcBuyItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcBuyItem update(@RequestBody ZcBuyItem zcBuyItem) {
        zcBuyItemDao.update(zcBuyItem);
        return zcBuyItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcBuyItem audit(@PathVariable Long id) {
        ZcBuyItem zcBuyItem = zcBuyItemDao.getById(id);
        zcBuyItem.setStatus(1);
        zcBuyItemDao.update(zcBuyItem);
        return zcBuyItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcBuyItem unaudit(@PathVariable Long id) {
        ZcBuyItem zcBuyItem = zcBuyItemDao.getById(id);
        zcBuyItem.setStatus(0);
        zcBuyItemDao.update(zcBuyItem);
        return zcBuyItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcBuyItemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcBuyItem> list(PageTableRequest request) {
                return zcBuyItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = zcBuyItemDao.count(request.getParams());

            List list = zcBuyItemDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcBuyItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcBuyItem> listAll() {
        List<ZcBuyItem> list = zcBuyItemDao.listAll();
        return list;
    }

    @GetMapping("/listByZcBuyId")
    @ApiOperation(value = "根据购买主表找到子表数据")
    public Map listByZcBfId(@RequestParam(value = "zcBuyId",required = false) Long zcBuyId,
                            @RequestParam(value = "cw",required = false) String cw) {
        Map map = new HashMap();
        List<Map<String,Object>> list = new ArrayList<>();
        if ( zcBuyId != null ) {
            list = zcBuyItemDao.listDetailByZcBfId(zcBuyId,cw);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 根据待办列表查询购买明细
     * @param todoId
     * @return
     */
    @GetMapping("/listByZcBuyIdNew")
    @ApiOperation(value = "根据待办列表查询购买明细")
    public Map listByZcBfIdNew(@RequestParam(value = "todoId",required = false) Long todoId) {
        Map map = new HashMap();
        List<Map<String,Object>> list = zcBuyItemDao.listDetailByFlowTodoIdNew(todoId);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 资产购买子项列表查询
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request,HttpServletRequest httpServletRequest) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:apply") > 0){
            //request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
            //request.getParams().put("type","user");
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:sh") > 0){
            //request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
            //request.getParams().put("type","gl");
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:cw") > 0){
            //request.getParams().put("cwUserId", UserUtil.getLoginUser().getDeptid());
            //request.getParams().put("type","cw");
        }
        Map map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);


        int count = zcBuyItemDao.countFinish(request.getParams());
        List<Map<String,Object>> list = zcBuyItemDao.listZcBuyFinish(request.getParams(), page*limit-limit, limit);


        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","查询成功");
        return map;
    }

}
