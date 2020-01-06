package com.itycu.server.controller;

import com.itycu.server.dao.*;
import com.itycu.server.dto.StockoutVO;
import com.itycu.server.dto.StockoutsVO;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.StockoutService;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stockoutss")
public class StockoutsController {

    @Autowired
    private StockoutsDao stockoutsDao;
    @Autowired
    private StockoutDao stockoutDao;
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private StockoutService stockoutService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Stockouts save(@RequestBody Stockouts stockouts) {
//        stockouts.setCreateby(UserUtil.getLoginUser().getId());
        stockoutsDao.save(stockouts);
        return stockouts;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StockoutsVO get(@PathVariable Long id) {
        return stockoutsDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Stockouts update(@RequestBody Stockouts stockouts) {
//        stockouts.setUpdateby(UserUtil.getLoginUser().getId());
        stockoutsDao.update(stockouts);
        return stockouts;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Stockouts audit(@PathVariable Long id) {
        Stockouts stockouts = stockoutsDao.getById(id);

//        stockouts.setAuditby(UserUtil.getLoginUser().getId());
//        stockouts.setAuditTime(new Date());
        stockouts.setStatus("1");
        stockoutsDao.update(stockouts);
        return stockouts;
    }
    @PutMapping("/auditZc/{id}")
    @ApiOperation(value = "确认装车")
    @Transactional
    public Stockouts auditZc(@PathVariable Long id) {
        Stockouts stockouts = stockoutsDao.getById(id);
//        stockoutService.checkImoney(stockouts);
        stockouts.setStatus("2");
        stockoutsDao.update(stockouts);

        List<StockoutsVO> byStockId = stockoutsDao.getByUnReject(stockouts.getPid());
        boolean isAllZc = true;  //是否全部装车
        if (!CollectionUtils.isEmpty(byStockId)){
            BigDecimal inum = new BigDecimal(0);
            BigDecimal imoney = new BigDecimal(0);
            for (StockoutsVO stockoutsVO:byStockId){
                  if (stockoutsVO.getInum() != null)  inum = inum.add(stockoutsVO.getInum());
                  if (stockoutsVO.getImoney() != null)  imoney = imoney.add(stockoutsVO.getImoney());
                  if (!"2".equals(stockoutsVO.getStatus()) && !"9".equals(stockoutsVO.getStatus())){
                       isAllZc = false;
                  }
            }
            StockoutVO byId = stockoutDao.getById(stockouts.getPid());
            byId.setInum(inum);
            byId.setImoney(imoney);
            byId.setItotal(imoney);

            if (byId.getTaxrate() != null){
                byId.setTax(imoney.multiply(byId.getTaxrate()));
//                    byId.setItotal(imoney.add(byId.getTax()));
            }
            if (isAllZc) {
                byId.setStatus("3");
            }

            stockoutDao.update(byId);

        }
        return stockouts;
    }


    //一键装车，调出需要确认装车的明细，然后调用确认装车函数
    @GetMapping("/auditzcall")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map auditzcall(PageTableRequest request) {


        Map map = new HashMap();
        List<Warehouse> warehouseList = warehouseDao.listbytypedept("产品库", UserUtil.getLoginUser().getDeptid());
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockouts:queryWhid") > 0){
            request.getParams().put("mxwhid", warehouseList.get(0).getId());
        }

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = stockoutsDao.count(request.getParams());

        List<StockoutsVO> list = stockoutsDao.list(request.getParams(), page*limit-limit, limit);
        for(StockoutsVO stockoutsVO:list){
            auditZc(stockoutsVO.getId());
        }


        return map;
    }



    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Stockouts unaudit(@PathVariable Long id) {
        Stockouts stockouts = stockoutsDao.getById(id);

//        stockouts.setAuditby(null);
//        stockouts.setAuditTime(null);
        stockouts.setStatus("0");
        stockoutsDao.update(stockouts);
        return stockouts;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return stockoutsDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<StockoutsVO> list(PageTableRequest request) {
                return stockoutsDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        stockoutsDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Stockouts> listAll() {
        List<Stockouts> list = stockoutsDao.listAll();
        return list;
    }

    @GetMapping("/listByStockoutId")
    @ApiOperation(value = "列出所有数据")
    public Map listByStockoutId(Long stockid) {
        Map map = new HashMap();

        List<StockoutsVO> list = stockoutsDao.getByStockId(stockid);

        map.put("data",list);
        map.put("code","0");
        map.put("msg","");

        return map;
    }


    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request) {
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockouts:queryown") > 0){
            request.getParams().put("createby", UserUtil.getLoginUser().getId());
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockouts:querydept") > 0){
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        if("xsStockout".equals(request.getParams().get("type"))){
            List<Warehouse> warehouseList = warehouseDao.listbytypedept("产品库", UserUtil.getLoginUser().getDeptid());
            request.getParams().put("mxwhid", warehouseList.get(0).getId());
        }

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = stockoutsDao.count(request.getParams());

        List list = stockoutsDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }



    @PostMapping("/export")
    @ApiOperation(value = "导出出库明细档案")
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<StockoutsVO> stockoutsVOS = stockoutsDao.listByCondition(request.getParams());
        String fileName = "出库明细表";
        if (request.getParams().get("groupby") != null && !"".equals(request.getParams().get("groupby"))){
            fileName = "出库汇总表";
        }
        if (!CollectionUtils.isEmpty(stockoutsVOS)) {
            String[] headers = new String[]{"仓库","日期","品名","规格","单位","件","件重","数量","单价","金额"};

            List<Object[]> datas = new ArrayList<>(stockoutsVOS.size());
            for (StockoutsVO stockoutsVO : stockoutsVOS) {
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String ddate = "";
                if(stockoutsVO.getDdate() != null){
                    ddate = s.format(stockoutsVO.getDdate());
                }
                Object[] objects = new Object[]{stockoutsVO.getWhname(),ddate,stockoutsVO.getInvname(),
                        stockoutsVO.getCpgg(),stockoutsVO.getDanwei(),stockoutsVO.getJian(),stockoutsVO.getJianzhong(),
                        stockoutsVO.getInum(),stockoutsVO.getIprice(),stockoutsVO.getImoney()};
                datas.add(objects);
            }

            ExcelUtil.excelExport(
                    fileName, headers,
                    datas, response);
        }
    }

    @GetMapping("/listByCondition")
    @ApiOperation(value = "列表")
    public Map listByCondition(PageTableRequest request) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockins:queryown") > 0){
            request.getParams().put("createby", UserUtil.getLoginUser().getId());
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockins:querydept") > 0){
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        if("xsStockout".equals(request.getParams().get("type"))){
            List<Warehouse> warehouseList = warehouseDao.listbytypedept("产品库", UserUtil.getLoginUser().getDeptid());
            request.getParams().put("mxwhid", warehouseList.get(0).getId());
        }

        Map map = new HashMap();

        List<StockoutsVO> stockinsVOS = stockoutsDao.listByCondition(request.getParams());

        map.put("data",stockinsVOS);
        map.put("code","0");
        map.put("msg","");

        return map;
    }


    @PostMapping("/fhexport")
    @ApiOperation(value = "导出发货统计")
    public void fhexport(PageTableRequest request, HttpServletResponse response) {
        List<StockoutsVO> stockoutsVOS = stockoutsDao.listByCondition(request.getParams());
        String fileName = "出库统计表";
        if (!CollectionUtils.isEmpty(stockoutsVOS)) {
            String[] headers = new String[]{"客户","仓库","日期","品名","规格","件","单件重","总数量","单位","单价","金额"};

            List<Object[]> datas = new ArrayList<>(stockoutsVOS.size());
            for (StockoutsVO stockoutsVO : stockoutsVOS) {
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String ddate = "";
                if(stockoutsVO.getDdate() != null){
                    ddate = s.format(stockoutsVO.getDdate());
                }
                Object[] objects = new Object[]{stockoutsVO.getCusname(),stockoutsVO.getWhname(),ddate,stockoutsVO.getInvname(),
                        stockoutsVO.getCpgg(),stockoutsVO.getJian(),stockoutsVO.getJianzhong(),
                        stockoutsVO.getInum(),stockoutsVO.getDanwei(),stockoutsVO.getIprice(),stockoutsVO.getImoney()};
                datas.add(objects);
            }

            ExcelUtil.excelExport(
                    fileName, headers,
                    datas, response);
        }
    }

}
