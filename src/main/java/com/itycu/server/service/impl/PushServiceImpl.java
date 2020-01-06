package com.itycu.server.service.impl;

import com.itycu.server.dao.PushmsgDao;
import com.itycu.server.dao.UserDao;
import com.itycu.server.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fanlinglong on 2018/9/8.
 */
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PushmsgDao pushmsgDao;


    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "3ajXdEBKAD6MhbNoBq546A";
    private static String appKey = "ueN25olrV5AdIHCCPt3TG1";
    private static String masterSecret = "m5sBI0KtQP9z6PX0SVN5Q3";
    private static String appUrl = "http://sdk.open.api.igexin.com/apiex.htm";

    @Override
    public String PushMsg(String title, String msg,Long userid,String url, Long todoid,Long bizid) {

//        if(url != null){
//            url = url.replace("/","、");
//        }
//
//        IGtPush push = new IGtPush(appUrl, appKey, masterSecret);
//
//        SysUser user = userDao.getById(userid);
//        String clientId = user.getClientid();
//
//        // 定义点击消息打开应用
//        TransmissionTemplate template = new TransmissionTemplate ();
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//        template.setTransmissionType(2);
//        template.setTransmissionContent("{title:"+title+",content:"+msg+"" +
//                "                         ,payload:{url:"+ url +",todoid:"+ todoid +",bizid:"+bizid+"}}");
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
//        //System.out.println(ret.getResponse().toString());
//
//        //保存到消息表
//        Pushmsg pushmsg = new Pushmsg();
//        pushmsg.setTitle(title);
//        pushmsg.setContent(msg);
//        pushmsg.setUserid(userid);
//        pushmsg.setUrl(url);
//        pushmsg.setTodoid(todoid);
//        pushmsg.setBizid(bizid);
//        pushmsg.setCreateby(UserUtil.getLoginUser().getId());
//        pushmsg.setStatus("0");
//        pushmsgDao.save(pushmsg);

        return "1";
    }
}
