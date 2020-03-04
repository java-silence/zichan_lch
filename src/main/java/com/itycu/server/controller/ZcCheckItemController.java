package com.itycu.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.itycu.server.dao.ZcCheckDao;
import com.itycu.server.dao.ZcCheckItemDao;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.ZcCheck;
import com.itycu.server.model.ZcCheckItem;
import com.itycu.server.model.ZcEpcCheckItem;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/zcCheckItems")
@Api(tags = "盘点--子表操作数据")
public class ZcCheckItemController {


    private static Logger logger = LoggerFactory.getLogger(ZcCheckItemController.class);

    @Autowired
    private ZcCheckDao zcCheckDao;

    @Autowired
    private ZcCheckItemDao zcCheckItemDao;

    //@Autowired
    //private ZcCheckItemService zcCheckItemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcCheckItem save(@RequestBody ZcCheckItem zcCheckItem) {
        zcCheckItemDao.save(zcCheckItem);
        return zcCheckItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcCheckItem get(@PathVariable Long id) {
        return zcCheckItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    @Transactional
    public Map update(@RequestBody List<ZcCheckItem> zcCheckItemList) {
        //更新找到的资产状态。
        Map map = new HashMap();
        try {
            for (int i = 0; i < zcCheckItemList.size(); i++) {
                zcCheckItemDao.update(zcCheckItemList.get(i));
            }
            map.put("code", "0");
            map.put("message", "成功");
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        return map;
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateAssetsState")
    @ApiOperation(value = "更新资产状态", tags = "更新资产状态")
    public Map updateAssetsState(String model, long zcCheckId) {
        Map finalMap = new HashMap();
        //更新找到的资产状态。
        Map map = new HashMap();
        logger.info("获得的Model===>  " + model);
        if (StringUtils.isEmpty(model)) {
            map.put("code", "500");
            map.put("message", "盘点的数据为空");
            return map;
        }
        try {
            List<ZcCheckItem> zcCheckItemList = JSONObject.parseArray(model, ZcCheckItem.class);
            if (!CollectionUtils.isEmpty(zcCheckItemList)) {
                logger.info("盘点数据的列表是：===========》》{}", zcCheckItemList);

                Map<String, Object> resultMap = insertProfitCheckItem(zcCheckItemList, zcCheckId);
                //   List<ZcCheckItem> profitList = (List) resultMap.get("profitList");
                List<ZcCheckItem> resultList = (List) resultMap.get("inCheckItemList");
                List<ZcCheckItem> notInCheckItem = (List) resultMap.get("notInCheckItemList");

                /**
                 *  提交的EpcID 不在资产信息表中存在
                 *  map.put("emptyEpcIdList", emptyEpcIdList);
                 *
                 *  提交的数据EpcId 在资产信息表中存在
                 * map.put("notEmptyEpcIdList", notEmptyEpcIdList);
                 *
                 *  提交的盘点数据在当前盘点单下面
                 * map.put("inCheckItemList", inCheckItemList);
                 *
                 * 提交的盘点数据不在当前的盘点单下面
                 *  map.put("notInCheckItemList", notInCheckItemList);
                 */

                if (CollectionUtils.isEmpty(resultList)) {
                    logger.info("盘点的数据全部是为空==={}", zcCheckId);
                    map.put("code", "0");
                    map.put("message", "成功");
                    map.put("data", null);
                    return map;
                }
                ZcCheckItem zcCheckItem = resultList.get(0);
                //没有盘点数据
                if (0 == zcCheckItem.getReCheck()) {
                    int result = updateZcCheck(resultList, zcCheckItem);
                    if (result > 0) {
                        finalMap.put("notCheck",queryNotFullCheckItem(zcCheckId));
                        finalMap.put("panying",queryPanYingCheckItem(zcCheckId));
                        map.put("code", "0");
                        map.put("message", "成功");
                        map.put("data", finalMap);

                    } else {
                        map.put("code", "500");
                        map.put("message", "操作失败");
                        map.put("data", null);
                    }
                    return map;
                } else {
                    //再次盘点
                    updateZcCheck(resultList, zcCheckItem);
                    long checkId = zcCheckItem.getZcCheckId();
                    long oldCheckId = zcCheckDao.queryOldCheckId(checkId);
                    logger.info("查询到的原始id=====>{}", oldCheckId);
                    int status = 1;
                    int result = 0;
                    //更新
                    for (int i = 0; i < resultList.size(); i++) {
                        long zcId = resultList.get(i).getZcId();
                        result = zcCheckItemDao.updateItemStatusByZid(oldCheckId, zcId, status);
                    }
                    if (result > 0) {
                        finalMap.put("notCheck",queryNotFullCheckItem(zcCheckId));
                        finalMap.put("panying",queryPanYingCheckItem(zcCheckId));
                        map.put("code", "0");
                        map.put("message", "成功");
                        map.put("data", finalMap);

                    } else {
                        map.put("code", "500");
                        map.put("message", "操作失败");
                        map.put("data", null);

                    }
                    return map;
                }
            } else {
                map.put("code", "500");
                map.put("message", "盘点的数据为空");
                map.put("data", null);

            }
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        return map;
    }


    private List<ZcEpcCheckItem> queryNotFullCheckItem(long zcCheckId) {
        List<ZcEpcCheckItem> zcCheckItemList = zcCheckItemDao.queryNotFullCheckItem(zcCheckId);
        return zcCheckItemList;
    }



    private List<ZcEpcCheckItem> queryPanYingCheckItem(long zcCheckId) {
        List<ZcEpcCheckItem> zcCheckItemList = zcCheckItemDao.queryPanYingCheckItem(zcCheckId);
        return zcCheckItemList;
    }



    /**
     * 过滤提交的数据中是否存在盘盈的数据，如果有直接插入到数据库中
     * 如果没有的话 就是将资产的列表的返回之后更新资产的状态
     */
    private Map<String, Object> insertProfitCheckItem(List<ZcCheckItem> zcCheckItemList, long zcCheckId) {
        Map<String, Object> map = new HashMap<>();
        //1  如果列表中有盘盈的数据，
        //2  找出需要盘盈的数据，加入到数据库中，更新数据的父表为盘盈的状态 ,字表插入盘盈的列表项
        //long zcCheckId = zcCkeckId;
        logger.info("【zcCheckId================>】" + zcCheckId);
        if (0 == zcCheckId) {
            return null;
        }
        //3
//        zcCheckItemList.forEach(k -> {
//            Long id = k.getZcId();
//            if (null == id || id == 0) {
//                k.setResult(String.valueOf(1));
//                k.setProfit(1);
//                k.setZcCheckId(zcCheckId);
//                k.setReCheck(zcCheckItemInfo.getReCheck());
//                //插入数据
//                profitList.add(k);
//            } else {
//                //啥也不干
//                zcCheckItem.add(k);
//            }
//        });
//        if (!CollectionUtils.isEmpty(profitList)) {
//            List<ZcCheckItem> items = new ArrayList<>();
//            //是盘盈的数据状态
//            int profit = 1;
//            profitList.forEach(k -> {
//                String epcId = k.getEpcid();
//                ZcInfoDto zcInfo = zcCheckDao.getByEpcId(epcId);
//                if (null != zcInfo) {
//                    logger.info("zcInfo获得的id===>>{},epcId===={}",zcInfo.getId(),epcId);
//                    k.setZcId(zcInfo.getId());
//                    items.add(k);
//                }
//            });
//
//            if (!CollectionUtils.isEmpty(items)) {
//                zcCheckDao.batchZcItem(profitList);
//                zcCheckDao.updateZcCheck(zcCheckId, profit);
//            }
//        }

        List<String> emptyEpcIdList = new ArrayList<>();
        List<String> notEmptyEpcIdList = new ArrayList<>();
        List<ZcCheckItem> inCheckItemList = new ArrayList<>();
        List<ZcInfoDto> notInCheckItemList = new ArrayList<>();
        zcCheckItemList.forEach(k -> {
            String epcId = k.getEpcid();
            ZcInfoDto zcInfo = zcCheckDao.getByEpcId(epcId);
            if (null != zcInfo) {
                notEmptyEpcIdList.add(epcId);
                long zcId = zcInfo.getId();
                //查询盘点的资产信息是否在当前的盘点记录的盘点单里面
                ZcCheckItem zcCheck = zcCheckItemDao.findZcItemInZcCheckSubList(zcId, zcCheckId);
                if (null != zcCheck) {
                    //设置为盘点中
                    zcCheck.setResult("1");
                    inCheckItemList.add(zcCheck);
                } else {
                    //
                    notInCheckItemList.add(zcInfo);
                }
            } else {
                emptyEpcIdList.add(epcId);
            }
        });
        map.put("emptyEpcIdList", emptyEpcIdList);
        map.put("notEmptyEpcIdList", notEmptyEpcIdList);
        map.put("inCheckItemList", inCheckItemList);
        map.put("notInCheckItemList", notInCheckItemList);
        logger.info("EpcId为空的数据 emptyEpcIdList =========>{}," +
                        "EpcId不为空数据 notEmptyEpcIdList===>{}, " +
                        "当前盘点单存在的数据 inCheckItemList===>{},不在当前盘点单中的数据 notInCheckItemList ===>{}",
                emptyEpcIdList, notEmptyEpcIdList, inCheckItemList, notInCheckItemList);


        /**
         * 盘盈数据处理方式
         */
        if (!CollectionUtils.isEmpty(notInCheckItemList)) {
            List<ZcCheckItem> result = new ArrayList<>();
            notInCheckItemList.forEach(k -> {
                ZcCheckItem zcCheckItem = new ZcCheckItem();
                //盘点完成
                zcCheckItem.setResult("1");
                zcCheckItem.setProfit(1);
                zcCheckItem.setFinishTime(new Date());
                zcCheckItem.setZcId(k.getId());
                zcCheckItem.setZcCheckId(zcCheckId);
                zcCheckItem.setDel(0);
                //没有再次复盘
                zcCheckItem.setReCheck(0);
                result.add(zcCheckItem);
            });
            int profit = 1;
            zcCheckDao.batchZcItem(result);
            zcCheckDao.updateZcCheck(zcCheckId, profit);
        }
        return map;
    }


    private int updateZcCheck(List<ZcCheckItem> zcCheckItemList, ZcCheckItem zcCheckItem) {
        //新的表单的数据
        for (int i = 0; i < zcCheckItemList.size(); i++) {
            zcCheckItemDao.update(zcCheckItemList.get(i));
        }
        //将盘点的父表更新为盘点状态盘点中
        long zcCheckId = zcCheckItem.getZcCheckId();
        int status = 1; //盘点中的状态
        return zcCheckDao.updateZcStatus(zcCheckId, status);
    }


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcCheckItemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcCheckItem> list(PageTableRequest request) {
                return zcCheckItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {
        Map map = new HashMap();
        logger.info("获取的请求参数是：" + request.getParams());

        request.getParams().put("del", "0");
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));

        int count = zcCheckItemDao.count(request.getParams());

        List list = zcCheckItemDao.list(request.getParams(), page * limit - limit, limit);

        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("message", "");

        return map;
    }

    @GetMapping("/getCheckItemList")
    @ApiOperation(value = "列表")
    @Transactional
    public Map list3(int zcCheckId,int offset,int limit) {
        Map map = new HashMap();
        List list = zcCheckItemDao.list2(zcCheckId, offset * limit - limit, limit);
        map.put("data", list);
        map.put("count", 0);
        map.put("code", "0");
        map.put("message", "");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcCheckItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcCheckItem> listAll() {
        List<ZcCheckItem> list = zcCheckItemDao.listAll();
        return list;
    }

    @GetMapping("/listByZcCheckId")
    @ApiOperation(value = "根据盘点主表找到子表数据")
    public Map listByZcDeployId(@RequestParam(value = "zcCheckId", required = false) Long zcCheckId) {
        Map map = new HashMap();
        List<Map<String, Object>> list = new ArrayList<>();
        if (zcCheckId != null) {
            list = zcCheckItemDao.listDetailByZcCheckId(zcCheckId);
        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }
}
