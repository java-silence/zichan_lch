package com.itycu.server.app.task;


import com.itycu.server.dao.ZcInspectDao;
import com.itycu.server.model.ZcInspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class InspectTask {


    @Autowired
    private ZcInspectDao zcInspectDao;


    //3.添加定时任务
    @Scheduled(cron = "0/5000 * * * * ?")
    private void configureTasks() {

        List<ZcInspect> zcInspectList = zcInspectDao.listAll();
        if (CollectionUtils.isEmpty(zcInspectList)) {
            return;
        }


        /**
         * TODO 重复巡检数据
         */
        List<Long> waitXunJianList = new ArrayList<>();
        zcInspectList.forEach(k -> {
            long zcId = k.getZcId();
            Integer waitXunJianZcId = zcInspectDao.queryXunjianAgainId(zcId);
            if (null == waitXunJianZcId) {
                waitXunJianList.add(zcId);
            }
        });


        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}

