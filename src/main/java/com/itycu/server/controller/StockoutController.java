package com.itycu.server.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.itycu.server.dao.*;
import com.itycu.server.dto.StockoutVO;
import com.itycu.server.dto.StockoutsVO;
import com.itycu.server.model.*;
import com.itycu.server.service.BeiyongjinService;
import com.itycu.server.service.StockoutService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/stockouts")
public class StockoutController {

    @Autowired
    private StockoutDao stockoutDao;

    @Autowired
    private StockoutsDao stockoutsDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private StockoutService stockoutService;

    @Autowired
    private CustomerclassDao customerclassDao;

    @Autowired
    private BeiyongjinService beiyongjinService;
    @Autowired
    private BeiyongjinDao beiyongjinDao;

    @PostMapping
    @ApiOperation(value = "保存")
    @Transactional
    public Stockout save(@RequestBody StockoutVO stockoutVO) {
        return stockoutService.save(stockoutVO);
//        Stockout stockout = (Stockout)stockoutVO;
//        stockout.setCreateby(UserUtil.getLoginUser().getId());
//        stockout.setStatus("0");
//        stockoutDao.save(stockout);
//
//        List<Stockouts> stockoutsList = stockoutVO.getStockoutsList();
//        if (stockoutsList.size()!=0){
//            stockoutsDao.saves(stockoutsList,stockout.getId());
//        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public StockoutVO get(@PathVariable Long id) {
        return stockoutDao.getById(id);
    }//

    @PutMapping
    @ApiOperation(value = "修改")
    public Stockout update(@RequestBody StockoutVO stockoutVO) {
        Stockout stockout = (Stockout)stockoutVO;

        List<Stockouts> stockoutsList = stockoutVO.getStockoutsList();
        if (stockoutsList.size()!=0){
            stockoutsDao.deletelist(stockout.getId());
            stockoutsDao.saves(stockoutsList,stockout.getId());
        }
        stockout.setUpdateby(UserUtil.getLoginUser().getId());
        stockoutDao.update(stockout);



        return stockout;
    }

    @PutMapping("/satchSave/{id}")
    @ApiOperation(value = "修改")
    public Stockout satchSave(@RequestBody StockoutVO stockoutVO,@PathVariable Long id) {
        Stockout stockout = (Stockout)stockoutVO;

        stockout.setUpdateby(UserUtil.getLoginUser().getId());
        stockoutDao.updateNew(stockout);

        return stockout;
    }

    Currstock findCurrstock(List<Currstock> currStocks, Long invid){
        List<Currstock> currstockList = currStocks.stream().filter(c -> c.getInvid().equals(invid)).collect(Collectors.toList());
        if (currstockList.size()==0){
            return null;
        }
        return currstockList.get(0);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    @Transactional
    public Stockout audit(@PathVariable Long id) {
        return stockoutService.audit(id);
    }


    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    @Transactional
    public Stockout unaudit(@PathVariable Long id) {
       return  stockoutService.unaudit(id);
    }

    @PutMapping("/unauditSm/{id}")
    @ApiOperation(value = "弃审")
    @Transactional
    public Stockout unauditSm(@PathVariable Long id) {
        Stockout stockout = stockoutDao.getById(id);

        stockout.setAuditby(null);
        stockout.setAuditTime(null);
        List<StockoutsVO> byStockId = stockoutsDao.getByStockId(id);
        if (!CollectionUtils.isEmpty(byStockId)){
            stockoutsDao.delzc(stockout.getId());
            for (StockoutsVO stockouts:byStockId){
                //如果明细中有已出库的就不能弃审
                if ("9".equals(stockouts.getStatus())){
                    throw new NullPointerException("弃审失败，"+stockouts.getInvname()+"-"+stockouts.getCpgg()+"已出库");
                }

                if ("3".equals(stockouts.getStatus())){
                    stockout.setImoney(stockout.getImoney().add(stockouts.getImoney()));
                    stockout.setItotal(stockout.getItotal().add(stockouts.getImoney()));
                }
            }
        }
        stockoutDao.update(stockout);
        stockoutDao.delzc(id,"0");

        return  stockout;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return stockoutDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<StockoutVO> list(PageTableRequest request) {
                return stockoutDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        Stockout stockout = stockoutDao.getById(id);
        if ("1".equals((stockout.getXsddtype()))){
            if(stockout.getFkfs().equals("现金")){
                if(stockout.getSsje()!=null){
                    Beiyongjin beiyongjin= new Beiyongjin();
                    beiyongjin.setJsr(stockout.getCreateby());
                    beiyongjin.setDizhi("零售现金收入【删除】");
                    beiyongjin.setZhichu(stockout.getSsje());
                    beiyongjin.setStatus("0");
                    beiyongjin.setCtype("zc");
                    beiyongjinService.save(beiyongjin);
                }

            }
        }

        stockoutDao.delete(id);
//        stockoutDao.updatestatusbypid(id,"0");
        stockoutsDao.deletelist(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Stockout> listAll() {
        List<Stockout> list = stockoutDao.listAll();
        return list;
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request) {
        if(request.getParams().get("ctype").equals("yl")){
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockoutyl:queryown") > 0){
                request.getParams().put("createby", UserUtil.getLoginUser().getId());
            }
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockoutyl:querydept") > 0){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }
        }else {
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockout:queryown") > 0){
                request.getParams().put("createby", UserUtil.getLoginUser().getId());
            }
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:stockout:querydept") > 0){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }
        }

        List<Customerclass> customerclasses = customerclassDao.listAll();
        if (request.getParams().get("xianlu") != null && !"".equals(request.getParams().get("xianlu"))){
            Long xianlu = Long.valueOf(request.getParams().get("xianlu").toString());
            List<Long> cusClass = new ArrayList<>();
            cusClass.add(xianlu);
            findXianluClass(customerclasses,xianlu,cusClass);
            if (!CollectionUtils.isEmpty(cusClass)){
                request.getParams().put("xianluClass", cusClass);
            }
        }
        if (request.getParams().get("gcjz") != null && !"".equals(request.getParams().get("gcjz"))){
            request.getParams().put("gcjzid", UserUtil.getLoginUser().getId());
        }

        request.getParams().put("del","0");
        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = stockoutDao.count(request.getParams());

        List<StockoutVO> list = stockoutDao.list(request.getParams(), page * limit - limit, limit);
//        if (!CollectionUtils.isEmpty(list)){
//            for (StockoutVO stockoutVO : list){
//                Customerclass xianlu = findXianlu(customerclasses, stockoutVO.getCid());
//                if (xianlu != null) stockoutVO.setXianlu(xianlu.getCname());
//            }
//        }
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    List<Customerclass> findCustomerClassByPid(List<Customerclass> customerclassList, Long pid){
        List<Customerclass> collect = new ArrayList<>();
        if (collect != null){
            collect = customerclassList.stream().filter(c -> c.getPid().equals(pid)).collect(Collectors.toList());
        }
        return collect;
    }

    //找到线路下的客户分类
    void findXianluClass(List<Customerclass> customerclassList, Long pid,List<Long> cusClass){
        List<Customerclass> customerClasses = findCustomerClassByPid(customerclassList, pid);
        if (!CollectionUtils.isEmpty(customerClasses)){
            for (Customerclass customerclass:customerClasses){
                cusClass.add(customerclass.getId());
                findXianluClass(customerclassList,customerclass.getId(),cusClass);
            }
        }else{
            return;
        }
    }

    List<Customerclass> findCustomerClass(List<Customerclass> customerclassList, Long id){
        List<Customerclass> collect = new ArrayList<>();
        if (collect != null){
            collect = customerclassList.stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList());
        }
        return collect;
    }

    //找到客户的线路
    Customerclass findXianlu(List<Customerclass> customerclassList, Long cid){
        List<Customerclass> customerClasses = findCustomerClass(customerclassList, cid);
        if (!CollectionUtils.isEmpty(customerClasses)){
            Customerclass customerclass = customerClasses.get(0);
            if ("线路".equals(customerclass.getC01())){
                return customerclass;
            }else{
                if (new Long(0).equals(customerclass.getPid()) || customerclass.getPid() == null){
                    return null;
                }else{
                    return findXianlu(customerclassList,customerclass.getPid());
                }
            }
        }else{
            return null;
        }
    }

    @GetMapping("/listByZhuangcheId")
    @ApiOperation(value = "根据装车id获取商贸订单明细")
    public Map listByZhuangcheId(Long zhuangcheid) {
        Map map = new HashMap();
        List<StockoutsVO> stockoutsVOS = new ArrayList<>();
        List<StockoutVO> stockoutVOS = stockoutDao.listByZhuangche(zhuangcheid);
        if (!CollectionUtils.isEmpty(stockoutVOS)){
            List<Long> stockoutIds = new ArrayList<>();
            for (StockoutVO stockoutVO:stockoutVOS){
                stockoutIds.add(stockoutVO.getId());
            }
            if (!CollectionUtils.isEmpty(stockoutIds)){
                List<StockoutsVO> byStockIds = stockoutsDao.getByStockIds(stockoutIds);
                stockoutsVOS.addAll(byStockIds);
            }
        }
        map.put("data",stockoutsVOS);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @GetMapping("/listZhuangcheId")
    @ApiOperation(value = "根据装车id获取商贸订单")
    public Map listZhuangcheId(Long zhuangcheid) {
        Map map = new HashMap();
        List<Customerclass> customerclasses = customerclassDao.listAll();
        List<StockoutVO> stockoutVOS = stockoutDao.listByZhuangche(zhuangcheid);
//        if (!CollectionUtils.isEmpty(stockoutVOS)){
//            for (StockoutVO stockoutVO : stockoutVOS){
//                Customerclass xianlu = findXianlu(customerclasses, stockoutVO.getCid());
//                if (xianlu != null) stockoutVO.setXianlu(xianlu.getCname());
//            }
//        }
        map.put("data",stockoutVOS);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @GetMapping("/listZhuangcheId_sm")
    @ApiOperation(value = "根据装车id获取商贸订单")
    public Map listZhuangcheId_sm(Long zhuangcheid) {
        Map map = new HashMap();
        List<Customerclass> customerclasses = customerclassDao.listAll();
        List<StockoutVO> stockoutVOS = stockoutDao.listByZhuangche_sm(zhuangcheid);
//        if (!CollectionUtils.isEmpty(stockoutVOS)){
//            for (StockoutVO stockoutVO : stockoutVOS){
//                Customerclass xianlu = findXianlu(customerclasses, stockoutVO.getCid());
//                if (xianlu != null) stockoutVO.setXianlu(xianlu.getCname());
//            }
//        }
        map.put("data",stockoutVOS);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @DeleteMapping("/delzc")
    @ApiOperation(value = "删除")
    public void delzc(Long id) {
        Stockout stockout = stockoutDao.getById(id);
        if(stockout.getDeptid()!=5L){
            stockoutDao.delzc(id,"1");
            stockoutsDao.delzc(id);
        }else {
            stockoutDao.delzc_baozhuang(id);
        }

    }

}
