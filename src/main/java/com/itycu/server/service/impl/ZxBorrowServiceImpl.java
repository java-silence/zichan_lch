package com.itycu.server.service.impl;

import com.itycu.server.dao.ZxBorrowDao;
import com.itycu.server.dao.ZxBorrowsDao;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.dto.ZxBorrowVO;
import com.itycu.server.model.Currstock;
import com.itycu.server.model.ZxBorrow;
import com.itycu.server.model.ZxBorrows;
import com.itycu.server.service.TodoService;
import com.itycu.server.service.ZxBorrowService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fanlinglong on 2018/12/23.
 */
@Service
public class ZxBorrowServiceImpl implements ZxBorrowService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZxBorrowDao zxBorrowDao;
    @Autowired
    private TodoService todoService;
    @Autowired
    private ZxBorrowsDao zxBorrowsDao;

    @Override
    public ZxBorrow save(ZxBorrowVO zxBorrowVO) {
        ZxBorrow zxBorrow = (ZxBorrow)zxBorrowVO;
        zxBorrow.setCreateby(UserUtil.getLoginUser().getId());
        zxBorrow.setStatus("0");
        zxBorrow.setDeptid(UserUtil.getLoginUser().getDeptid());
        zxBorrow.setFlowid(3L);
        zxBorrow.setStepid(2L);

        zxBorrowDao.save(zxBorrow);

        List<ZxBorrows> zxBorrowsList = zxBorrowVO.getZxBorrowsList();
        if (!CollectionUtils.isEmpty(zxBorrowsList)){
              zxBorrowsDao.saves(zxBorrowsList,zxBorrow.getId());
        }

        TodoDto todo = new TodoDto();
        todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() +"】设备借用申请");
        todo.setSendby(zxBorrow.getCreateby());
        todo.setBizid(zxBorrow.getId());
        todo.setFlowid(zxBorrow.getFlowid());
        todo.setStepid(1L);
        todo.setStatus("0");
        todo.setBizcreateby(zxBorrow.getCreateby());
        todo.setBizdeptid(zxBorrow.getDeptid());
        todo.setBiztable("zx_borrow");

        todoService.sendTodo(todo);

        log.debug("新增设备借用申请单{}", zxBorrow.getCreateby());

        return zxBorrow;
    }

    @Override
    public TodoDto todo(TodoDto todoDto) {
        todoService.update(todoDto);
        //中心库管审批
        if(todoDto.getBack().equals("0") && todoDto.getStepid()==4 ){
            ZxBorrow zxBorrow = zxBorrowDao.getById(todoDto.getBizid());

            zxBorrow.setUpdateby(UserUtil.getLoginUser().getId());
            zxBorrow.setUpdateTime(new Date());
            zxBorrow.setStatus("1");
            zxBorrowDao.update(zxBorrow);


        }

        return todoDto;
    }

    Currstock findCurrstock(List<Currstock> currStocks, Long invid){
        List<Currstock> currstockList = currStocks.stream().filter(c -> c.getInvid().equals(invid)).collect(Collectors.toList());
        if (currstockList.size()==0){
            return null;
        }
        return currstockList.get(0);
    }
}
