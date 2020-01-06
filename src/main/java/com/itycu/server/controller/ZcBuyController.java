package com.itycu.server.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.TodoDao;
import com.itycu.server.dao.ZcBuyItemDao;
import com.itycu.server.dto.ZcBfCheckDto;
import com.itycu.server.dto.ZcBuyCheckDto;
import com.itycu.server.dto.ZcBuyDto;
import com.itycu.server.service.ZcBuyService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcBuyDao;
import com.itycu.server.model.ZcBuy;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcBuys")
public class ZcBuyController {

    /** 审核跳转路径 */
    public static final String actionUrl = "zcbuy/auditZcBuy.html";

    @Autowired
    private ZcBuyDao zcBuyDao;

    @Autowired
    private ZcBuyService zcBuyService;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private ZcBuyItemDao zcBuyItemDao;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存资产购买申请")
    @Transactional
    public Map save(@RequestBody ZcBuyDto zcBuyDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Long id = zcBuyDto.getId();
            if (!ObjectUtils.isEmpty(id)) {
                // 修改
                zcBuyService.updateZcBuy(zcBuyDto);
            }else {
                ZcBuyDto buyDto = zcBuyService.save(zcBuyDto);
            }
            map.put("code","0");
            map.put("msg","添加成功");
            return map;
        } catch (Exception e) {
            map.put("code","1");
            map.put("msg",e.getMessage());
            return map;
        }
    }

    @LogAnnotation
    @PostMapping("/check")
    @ApiOperation(value = "资产购买审核")
    @Transactional
    public Map check(@RequestBody ZcBuyCheckDto zcBuyCheckDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String itemStatus = zcBuyService.check(zcBuyCheckDto);
            map.put("code","0");
            map.put("msg","审核成功");
            map.put("status",itemStatus);
            return map;
        } catch (Exception e) {
            map.put("code","1");
            map.put("msg",e.getMessage());
            return map;
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Map get(@PathVariable Long id) {
        Map map = new HashMap<>();
        ZcBuy zcBuy = zcBuyDao.getById(id);
        Map<String, Object> shenqing = new HashMap<>();
        Map<String, Object> shenhe = new HashMap<>();
        Map<String, Object> caiwu = new HashMap<>();
        // 申请人信息
        HashMap<String, Object> zcBuyDetail = zcBuyDao.getZcBuyDetail(id);
        shenqing.put("updateTime",zcBuyDetail.get("createTime"));
        shenqing.put("username",zcBuyDetail.get("username"));
        shenqing.put("nickname",zcBuyDetail.get("nickname"));
        shenqing.put("deptname",zcBuyDetail.get("deptname"));
        // 审核人信息
        List<Map<String,Object>> list = todoDao.findAuditors(id,actionUrl);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0){
                shenhe.put("updateTime",list.get(i).get("updateTime"));
                shenhe.put("username",list.get(i).get("username"));
                shenhe.put("nickname",list.get(i).get("nickname"));
                shenhe.put("deptname",list.get(i).get("deptname"));
            }
            if (i == 1){
                caiwu.put("updateTime",list.get(i).get("updateTime"));
                caiwu.put("username",list.get(i).get("username"));
                caiwu.put("nickname",list.get(i).get("nickname"));
                caiwu.put("deptname",list.get(i).get("deptname"));
            }
        }
        map.put("zcBuy",zcBuy);
        map.put("shenqing",shenqing);
        map.put("shenhe",shenhe);
        map.put("caiwu",caiwu);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcBuy update(@RequestBody ZcBuy zcBuy) {
        zcBuyDao.update(zcBuy);
        return zcBuy;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcBuy audit(@PathVariable Long id) {
        ZcBuy zcBuy = zcBuyDao.getById(id);
        zcBuy.setStatus(1);
        zcBuyDao.update(zcBuy);
        return zcBuy;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcBuy unaudit(@PathVariable Long id) {
        ZcBuy zcBuy = zcBuyDao.getById(id);
        zcBuy.setStatus(0);
        zcBuyDao.update(zcBuy);
        return zcBuy;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcBuyDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcBuy> list(PageTableRequest request) {
                return zcBuyDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = zcBuyDao.count(request.getParams());

            List list = zcBuyDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request,HttpServletRequest httpServletRequest) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:apply") > 0){
            request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
            request.getParams().put("type","user");
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:sh") > 0){
            request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
            request.getParams().put("type","gl");
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:buycheck:cw") > 0){
            request.getParams().put("cwUserId", UserUtil.getLoginUser().getDeptid());
            request.getParams().put("type","cw");
        }
        Map map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        // todo 此处需要添加权限
        int count = zcBuyDao.count(request.getParams());
        List<Map<String,Object>> list = zcBuyDao.listZcBuy(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","查询成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcBuyDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcBuy> listAll() {
        List<ZcBuy> list = zcBuyDao.listAll();
        return list;
    }

    @GetMapping("/getZcBuyDetail")
    @ApiOperation(value = "根据id获取")
    public Map getZcBfDetail(@RequestParam(value = "buyId",required = false) Long buyId) {
        Map map = new HashMap();
        HashMap<String,Object> data = zcBuyDao.getZcBuyDetail(buyId);
        map.put("data",data);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    /**
     * 购买流程启动
     * @param zcBuyId
     */
    @GetMapping("/startBuyProcess/{id}")
    public Map startBuyProcess(@PathVariable("id") String zcBuyId){
        HashMap<String, Object> map = new HashMap<>();
        try {
            zcBuyService.startProcess(zcBuyId);
            map.put("code","0");
            map.put("msg","启动成功");
            return map;
        } catch (Exception e) {
            map.put("code","1");
            map.put("msg",e.getMessage());
            return map;
        }
    }

    /**
     * 数据导出
     * @param request
     * @param httpServletRequest
     * @param response
     */
    @PostMapping("/export")
    @ApiOperation(value = "列表")
    public void exportData(PageTableRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response) {

        Integer page = 1;
        Integer limit = Integer.MAX_VALUE;
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
        List<Map<String,Object>> list = zcBuyItemDao.listZcBuyFinish(request.getParams(), page*limit-limit, limit);

        String fileName = "购买记录";
        String[] headers = new String[]{
                "购买单号", "购买名称", "规格型号", "单位",
                "产品品牌", "采购单价", "采购数量", "采购总价",
                "供应商名称", "用途", "申请部门", "管理部门",
                "申请人", "申请账号", "申请时间", "完成时间", "备注"
        };
        List<Object[]> datas = new ArrayList<>(list.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map zcInfo : list) {
            String applyTime = (String) zcInfo.get("applyTime");
            String useTime = "";
            if (zcInfo.get("startUseTime") != null && !StringUtils.isEmpty(zcInfo.get("startUseTime"))) {
                useTime = s.format((Date) zcInfo.get("startUseTime"));
            }
            Object[] objects = new Object[]{
                    zcInfo.get("buyNum"), zcInfo.get("name"), zcInfo.get("model"), zcInfo.get("unit"),
                    zcInfo.get("brand"), zcInfo.get("price"), zcInfo.get("num"),
                    zcInfo.get("totalPrice"), zcInfo.get("supplierName"), zcInfo.get("useDes"), zcInfo.get("sydeptname"),
                    zcInfo.get("gldeptname"), zcInfo.get("applyNickname"), zcInfo.get("username"), zcInfo.get("applyTime"),
                    zcInfo.get("finishTime"), zcInfo.get("zc_bz")
            };
            datas.add(objects);
        }
        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
    }

}
