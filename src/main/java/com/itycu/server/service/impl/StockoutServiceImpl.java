package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.*;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.BeiyongjinService;
import com.itycu.server.service.StockoutService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.OutputStream;

/**
 * Created by fanlinglong on 2019/1/2.
 */
@Service
public class StockoutServiceImpl implements StockoutService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private TodoService todoService;
    @Autowired
    private StockoutDao stockoutDao;
    @Autowired
    private StockoutsDao stockoutsDao;

    @Autowired
    private CustomerclassDao customerclassDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DictDao dictDao;

   @Autowired
   private BeiyongjinService beiyongjinService;
   @Autowired
   private BeiyongjinDao beiyongjinDao;

    @Override
    @Transactional
    public Stockout save(StockoutVO stockoutVO) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd") ;
        Stockout stockout = (Stockout)stockoutVO;
        if(stockout.getCreateby() ==null) stockout.setCreateby(UserUtil.getLoginUser().getId());
        stockout.setStatus("0");
        stockout.setDel("0");
        stockout.setPrintstatus("0");
        if(stockout.getDeptid() ==null) stockout.setDeptid(UserUtil.getLoginUser().getDeptid());

        if(stockout.getCcode() ==null) stockout.setCcode("");
        if(stockout.getCcode().equals("") ){
            if(stockout.getDeptid().equals(6L)){
                stockout.setCcode(maxcode("SM"));
            }else {
                stockout.setCcode(maxcode("CK"));
            }
        }

        if(stockout.getDdate() == null){
            try{
                stockout.setDdate(df.parse(df.format(new Date())));
            }catch (ParseException e){
                e.printStackTrace();
            }
        }


        stockout.setFlowid(6L);
        stockout.setStepid(2L);
        stockoutDao.save(stockout);

        List<Stockouts> stockoutsList = stockoutVO.getStockoutsList();

        if (stockoutsList.size()!=0){
            for (Stockouts stockouts : stockoutsList){
                stockouts.setStatus("0");
            }
            stockoutsDao.saves(stockoutsList,stockout.getId());
        }

        if(stockoutVO.getIsaudit().equals("1")){
            try{
                audit(stockout.getId());
            }catch (Exception ex){
                throw new NullPointerException("出库审核失败"+ ex.getMessage().toString());
            }
        }


        if ("1".equals((stockout.getXsddtype())) && "sm".equals(stockout.getCtype())){
            if(stockout.getFkfs().equals("现金")){
                if(stockout.getSsje()!=null && stockout.getSsje().doubleValue()>0){
                    Beiyongjin beiyongjin= new Beiyongjin();
                    beiyongjin.setBussid(stockout.getId());
                    beiyongjin.setJsr(stockout.getCreateby());
                    beiyongjin.setDizhi("零售现金收入【新增】");
                    beiyongjin.setShouru(stockout.getSsje());
                    beiyongjin.setStatus("0");
                    beiyongjin.setCtype("sr");
                    beiyongjinService.save(beiyongjin);
                }
            }
        }


//        TodoDto todo = new TodoDto();
//        todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() +"-备件入库】" + stockout.getMemo());
//        todo.setSendby(stockout.getCreateby());
//        todo.setBizid(stockout.getId());
//        todo.setFlowid(stockout.getFlowid());
//        todo.setStepid(1);
//        todo.setStatus("0");
//        todo.setBizcreateby(stockout.getCreateby());
//        todo.setBizdeptid(stockout.getDeptid());
//        todo.setBiztable("kc_stockout");
//        todoService.sendTodo(todo);

        log.debug("新增出库单{}", stockout.getCreateby() + stockout.getMemo());

        return stockout;
    }

    @Override
    public TodoDto todo(TodoDto todoDto) {
        todoService.update(todoDto);
//        if(todoDto.getBack().equals("0") && todoDto.getStepid()==2 ){
//        }
        return todoDto;
    }

    @Override
    @Transactional
    public Stockout audit(Long id) {
        StockoutVO stockout = stockoutDao.getById(id);

        if(stockout.getAuditby()==null) stockout.setAuditby(UserUtil.getLoginUser().getId());
        stockout.setAuditTime(new Date());
        if (stockout.getDeptid()==6L){
            if(stockout.getStatus().equals("9")) return stockout;   //如状态为9即已审，不再重复审核
            stockout.setStatus("9");
        }else {
            if(stockout.getStatus().equals("1")) return stockout;   //如状态为1即已审，不再重复审核
            stockout.setStatus("1");
        }

        stockoutDao.update(stockout);
        //更新明细状态与表头状态一致  表体出库完成后统一为9
        stockoutDao.updatestatusbypid(stockout.getId(), "9");

        return stockout;
    }

    @Override
    @Transactional
    public Stockout unaudit(Long id) {
        StockoutVO stockout = stockoutDao.getById(id);
        if(stockout.getStatus().equals("0")) return stockout;   //如状态为0为未审，不能弃审
        stockout.setAuditby(null);
        stockout.setAuditTime(null);
        stockout.setStatus("0");
        stockoutDao.update(stockout);

        return stockout;
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
            throw new NullPointerException("基础档案-客户分类中没有id为"+cid+"的分类");
        }
    }




    public List<Stockouts> findStockouts(List<Stockouts> stockoutsList,Long stocksId){
        List<Stockouts> collect = new ArrayList<>();
        if (stocksId != null){
            collect = stockoutsList.stream().filter(sc -> stocksId.equals(sc.getId())).collect(Collectors.toList());
        }
        return collect;
    }

    //提取最大订单号
    private String maxcode(String ccodetype){
        String ccode;
        Date currentTime = new Date();
        int endNum ;
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        ccode = ccodetype + formatter.format(currentTime);
        String maxno =  stockoutDao.getMaxCcode(ccode + "%");
        if(maxno!=null && maxno.contains(ccode)){
            endNum = Integer.parseInt(maxno.substring(maxno.length()-4,maxno.length() ));
            ccode += String.format("%04d", endNum +1);
        }else {
            ccode += "0001";
        }
        return ccode;
    }



}
