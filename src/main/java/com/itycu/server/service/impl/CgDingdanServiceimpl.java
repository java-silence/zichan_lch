package com.itycu.server.service.impl;

import com.itycu.server.dao.CgDingdanDao;
import com.itycu.server.dao.CgDingdansDao;
import com.itycu.server.dto.CgdingdanVO;
import com.itycu.server.model.CgDingdan;
import com.itycu.server.model.CgDingdans;
import com.itycu.server.service.CgDingdanService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2019/4/10.
 */
@Service
public class CgDingdanServiceimpl implements CgDingdanService {

    @Autowired
    private CgDingdanDao cgDingdanDao;

    @Autowired
    private CgDingdansDao cgDingdansDao;

    //    @Autowired
//    private CgDingdan cgDingdan;
    @Override
    public CgdingdanVO save(CgdingdanVO cgdingdanVO) {

        CgDingdan cgDingdan = (CgDingdan) cgdingdanVO;
        cgDingdan.setCreateby(UserUtil.getLoginUser().getId());
        cgDingdan.setStatus("0");
        cgDingdan.setDeptid(UserUtil.getLoginUser().getDeptid());
        cgDingdan.setBussid(cgdingdanVO.getBussid());

        cgDingdan.setCcode(maxcode());


        cgDingdanDao.save(cgDingdan);

        List<CgDingdans> cgDingdansList = cgdingdanVO.getCgDingdansList();
        if (cgDingdansList.size() != 0) {
            cgDingdansDao.saves(cgDingdansList, cgDingdan.getId());
        }

//            TodoDto todo = new TodoDto();
//            todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() +"-备件入库】" + stockin.getMemo());
//            todo.setSendby(stockin.getCreateby());
//            todo.setBizid(stockin.getId());
//            todo.setFlowid(stockin.getFlowid());
//            todo.setStepid(1);
//            todo.setStatus("0");
//            todo.setBizcreateby(stockin.getCreateby());
//            todo.setBizdeptid(stockin.getDeptid());
//            todo.setBiztable("kc_stockin");
//
//            todoService.sendTodo(todo);

//            log.debug("新增备件入库申请单{}", stockin.getCreateby() + stockin.getMemo());

        return cgdingdanVO;
    }

    @Override
    public CgdingdanVO update(CgdingdanVO cgdingdanVO) {

        cgdingdanVO.setUpdateby(UserUtil.getLoginUser().getId());
        cgDingdanDao.update(cgdingdanVO);
        List<CgDingdans> cgJihuasList = cgdingdanVO.getCgDingdansList();
        if (cgJihuasList.size()!=0){
            cgDingdansDao.delbypid(cgdingdanVO.getId());
            cgDingdansDao.saves(cgJihuasList,cgdingdanVO.getId());
        }

        return cgdingdanVO;
    }

    private String maxcode(){
        String ccode;
        Date currentTime = new Date();
        int endNum ;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        ccode = "CGDD" + formatter.format(currentTime);
        String maxno = cgDingdanDao.getMaxCcode();
        if(maxno!=null && maxno.contains(ccode)){
            endNum = Integer.parseInt(maxno.substring(maxno.length()-4,maxno.length() ));
            ccode += String.format("%04d", endNum +1);
        }else {
            ccode += "0001";
        }
        return ccode;
    }
}
