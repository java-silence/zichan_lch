package com.itycu.server.service.impl;

import com.itycu.server.dao.CgDingdanDao;
import com.itycu.server.dao.FukuanshenqingDao;
import com.itycu.server.dao.TodoDao;
import com.itycu.server.dao.VendorDao;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.CgDingdan;
import com.itycu.server.model.Fukuanshenqing;
import com.itycu.server.service.FukuanshenqingService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2019-04-09.
 */
@Service
public class FukuanshenqingServiceImpl implements FukuanshenqingService {

    @Autowired
    private FukuanshenqingDao fukuanshenqingDao;
    @Autowired
    private TodoService todoService;

    @Autowired
    private VendorDao vendorDao;

    @Autowired
    private CgDingdanDao cgDingdanDao;

    @Override
    public Fukuanshenqing save(Fukuanshenqing fukuanshenqing) {


        fukuanshenqing.setCreateby(UserUtil.getLoginUser().getId());
        fukuanshenqing.setDeptid(UserUtil.getLoginUser().getDeptid());
        if(fukuanshenqing.getFkfs().equals("对公账户")){
            fukuanshenqing.setFlowid(13L);
        }else {
            fukuanshenqing.setFlowid(14L);
        }

        fukuanshenqingDao.save(fukuanshenqing);

        TodoDto todo = new TodoDto();
        todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() +"】付" + vendorDao.getById(fukuanshenqing.getKsid()).getCname() + fukuanshenqing.getFkxm()+ fukuanshenqing.getFkje());
        todo.setSendby(fukuanshenqing.getCreateby());
        todo.setBizid(fukuanshenqing.getId());
        todo.setFlowid(fukuanshenqing.getFlowid());
        todo.setStepid(1L);
        todo.setStatus("0");
        todo.setBizcreateby(fukuanshenqing.getCreateby());
        todo.setBizdeptid(fukuanshenqing.getDeptid());
        todo.setBiztable("cw_fukuanshenqing");

        todoService.sendTodo(todo);

        saveFiles(fukuanshenqing.getId(),fukuanshenqing.getFlowid().toString(),fukuanshenqing.getFileIds());
        return null;
    }

    @Override
    public TodoDto todo(TodoDto todo) {
        todoService.update(todo);
        saveFiles(todo.getId(),todo.getFlowid().toString(),todo.getFileIds());
        Fukuanshenqing fukuanshenqing = fukuanshenqingDao.getById(todo.getBizid());
        if(todo.getStepid() == 2L){
            fukuanshenqing.setStatus("1");
            fukuanshenqing.setAuditby(UserUtil.getLoginUser().getId());
            fukuanshenqing.setAuditTime(new Date());
            fukuanshenqingDao.update(fukuanshenqing);
            CgDingdan cgDingdan = cgDingdanDao.getById(fukuanshenqing.getBussid());
            if(cgDingdan != null){
                if(cgDingdan.getYfkje()==null) cgDingdan.setYfkje(new BigDecimal(0));
                cgDingdan.setYfkje(cgDingdan.getYfkje().add(fukuanshenqing.getFkje()));
                cgDingdanDao.update(cgDingdan);
            }
        }
        return todo;
    }

    private void saveFiles(long fukuanshengqingId, String biztype, List<String> fileIds){
        if(fileIds != null && fileIds.size()>0){
            fukuanshenqingDao.saveFiles(fukuanshengqingId,biztype,fileIds );
        }
    }
}
