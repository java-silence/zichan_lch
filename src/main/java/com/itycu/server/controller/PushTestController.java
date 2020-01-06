package com.itycu.server.controller;

import com.itycu.server.dao.UserDao;
import com.itycu.server.service.impl.PushServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fanlinglong on 2018/7/10 0010.
 */
@RestController
@RequestMapping("/pushTest")
public class PushTestController {
    @Autowired
    private UserDao userDao;

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "3ajXdEBKAD6MhbNoBq546A";
    private static String appKey = "ueN25olrV5AdIHCCPt3TG1";
    private static String masterSecret = "m5sBI0KtQP9z6PX0SVN5Q3";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    @Autowired
    private PushServiceImpl pushService;

    @PostMapping("/test")
    public void pushTest(String title,String msg,Long userid) {

        pushService.PushMsg( title, msg,userid,null,0L,0L);

//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//
//        SysUser user = userDao.getById(userid);
//        String clientId = user.getClientid();
//
//        // 定义点击消息打开应用
//        NotificationTemplate template = new NotificationTemplate();
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//        template.setTitle(title);
//        template.setText(msg);
//        template.setTransmissionType(2);
//
//        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
//        SingleMessage message = new SingleMessage();
//        message.setData(template);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1000 * 600);
//
//        Target target = new Target();
//        target.setAppId(appId);
//        target.setClientId(clientId);
//
//        IPushResult ret = push.pushMessageToSingle(message,target);
//        System.out.println(ret.getResponse().toString());
    }
}
