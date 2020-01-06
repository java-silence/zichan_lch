package com.itycu.server.controller;

import java.util.*;
import java.util.stream.Collectors;
import com.itycu.server.dao.FlowTodoItemDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.ZcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcBfItemDao;
import com.itycu.server.model.ZcBfItem;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcBfItems")
public class ZcBfItemController {

    @Autowired
    private ZcBfItemDao zcBfItemDao;

    @Autowired
    private ZcInfoDao zcInfoDao;

    @Autowired
    private FlowTodoItemDao flowTodoItemDao;

    //@Autowired
    //private ZcBfItemService zcBfItemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcBfItem save(@RequestBody ZcBfItem zcBfItem) {
//        zcBfItem.setCreateby(UserUtil.getLoginUser().getId());
        zcBfItemDao.save(zcBfItem);
        return zcBfItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcBfItem get(@PathVariable Long id) {
        return zcBfItemDao.getById(id);
    }

    @GetMapping("/updateItem")
    @ApiOperation(value = "修改附件")
    public Map updateItem(@RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "url",required = false) String url,
                               @RequestParam(value = "id",required = false) Long id) {
        Map<String, Object> map = new HashMap<>();
        FlowTodoItem item = flowTodoItemDao.getById(id);
        zcBfItemDao.updateById(item.getFlowItemId(),name,url);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 修改鉴定附件
     * @param name 附件名称
     * @param url  附件地址
     * @param itemIds 资产itemid集合
     * @return
     */
    @GetMapping("/updateIdentity")
    @ApiOperation(value = "修改鉴定附件")
    public Map updateIdentity(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "url",required = false) String url,
                              @RequestParam(value = "itemIds",required = false) String itemIds) {
        Map<String, Object> map = new HashMap<>();
        String[] array = itemIds.split(",");
        List<String> ids = Arrays.asList(array);
        zcBfItemDao.updateZcbfItemList(name,url,ids);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcBfItem update(@RequestBody ZcBfItem zcBfItem) {
//        zcBfItem.setUpdateby(UserUtil.getLoginUser().getId());
        zcBfItemDao.update(zcBfItem);
        return zcBfItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcBfItem audit(@PathVariable Long id) {
        ZcBfItem zcBfItem = zcBfItemDao.getById(id);

//        zcBfItem.setAuditby(UserUtil.getLoginUser().getId());
//        zcBfItem.setAuditTime(new Date());
//        zcBfItem.setStatus("1");
        zcBfItemDao.update(zcBfItem);
        return zcBfItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcBfItem unaudit(@PathVariable Long id) {
        ZcBfItem zcBfItem = zcBfItemDao.getById(id);

//        zcBfItem.setAuditby(null);
//        zcBfItem.setAuditTime(null);
//        zcBfItem.setStatus("0");
        zcBfItemDao.update(zcBfItem);
        return zcBfItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcBfItemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcBfItem> list(PageTableRequest request) {
                return zcBfItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = zcBfItemDao.count(request.getParams());

            List list = zcBfItemDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcBfItemDao.delete(id);
    }

    @GetMapping("/delItem/{id}")
    @ApiOperation(value = "删除")
    public Map deleteItem(@PathVariable Long id) {
        Map map = new HashMap();
//        ZcBfItem zcBfItem = new ZcBfItem();
//        zcBfItem.setId(id);
//        zcBfItem.setDel(1);
//        zcBfItem.setUpdateTime(new Date());
//        zcBfItemDao.update(zcBfItem);
        zcBfItemDao.delete(id);
        //map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcBfItem> listAll() {
        List<ZcBfItem> list = zcBfItemDao.listAll();
        return list;
    }

    /**
     * 根据报废ID查询
     * @param zcBfId
     * @return
     */
    @GetMapping("/listByZcBfId")
    @ApiOperation(value = "根据报废主表找到子表数据")
    public Map listByZcBfId(@RequestParam(value = "zcBfId",required = false) Long zcBfId) {
        Map map = new HashMap();
        //List<ZcBfItem> list = zcBfItemDao.listByZcBfId(zcBfId);
        List<Map<String,Object>> list = new ArrayList<>();
        if ( zcBfId != null ) {
            list = zcBfItemDao.listDetailByZcBfId(zcBfId);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * @param todoid
     * @return
     */
    @GetMapping("/listtodoid")
    @ApiOperation(value = "根据报废主表找到子表数据")
    public Map listtodoid(@RequestParam(value = "todoid",required = false) Long todoid) {
        Map map = new HashMap();
        //List<ZcBfItem> list = zcBfItemDao.listByZcBfId(zcBfId);

        List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(todoid);
        List<Long> bfitemids = flowTodoItems.stream().map(e -> e.getFlowItemId()).collect(Collectors.toList());

        List<Map<String,Object>> list = new ArrayList<>();
//        if ( zcBfId != null ) {
//            list = zcBfItemDao.listDetailByZcBfId(zcBfId);
//        }
        list = zcBfItemDao.listByItemIds(bfitemids);

        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }


    @GetMapping("/listByZcBfIdNew")
    @ApiOperation(value = "根据报废主表找到子表数据")
    public Map listByZcBfIdNew(@RequestParam(value = "todoId",required = false) Long todoId,
                               @RequestParam(value = "type",required = false) String type) {
        Map map = new HashMap();
        List<Map<String,Object>> list = null;
        list = zcBfItemDao.listDetailByFlowTodoIdNew(todoId);
        if (!ObjectUtils.isEmpty(type)) {
            list = zcBfItemDao.listDetailByFlowTodoIdNewByType(todoId,type);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 根据报废ID查询
     * @param bz
     * @return
     */
    @PostMapping("/startItemProcess")
    @ApiOperation(value = "根据报废主表找到子表数据")
    public Map startItemProcess(@RequestParam(value = "id",required = false) Long id,
                                @RequestParam(value = "bz",required = false) String bz) {
        Map map = new HashMap();
        ZcBfItem zcBfItem = new ZcBfItem();
        zcBfItem.setId(id);
        zcBfItem.setBz(bz);
        zcBfItem.setShbStatus("0");
        zcBfItem.setStatus(1);
        zcBfItem.setUpdateTime(new Date());
        zcBfItemDao.update(zcBfItem);
        //map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @GetMapping("/getByZcBfId/{id}")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map layuiList(@PathVariable("id") String todoId) {


        Map map = new HashMap();
//
//        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
//        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
//
//        int count = zcBfDao.count(request.getParams());
//
//        //List list = zcBfDao.list(request.getParams(), page*limit-limit, limit);
//        List<Map<String,Object>> list = zcBfDao.listZcbf(request.getParams(), page*limit-limit, limit);

//        map.put("data",list);
//        map.put("count",count);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    /**
     * 资产恢复正常
     * @param bfItemId
     * @return
     */
    @GetMapping("/huifu/{id}")
    @ApiOperation(value = "列表")
    //@PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map zcHuiFu(@PathVariable("id") Long bfItemId) {
        Map map = new HashMap();
        // 获取资产ID
        ZcBfItem zcBfItem = zcBfItemDao.getById(bfItemId);
        zcBfItem.setShbStatus("4");
        zcBfItemDao.update(zcBfItem);
        Long zcId = zcBfItem.getZcId();
        ZcInfo zcInfo = new ZcInfo();
        zcInfo.setCreateTime(null);
        zcInfo.setId(zcId);
        zcInfo.setUseStatus(1);
        zcInfoDao.update(zcInfo);
        map.put("code","0");
        map.put("msg","修改成功");
        return map;
    }

}
