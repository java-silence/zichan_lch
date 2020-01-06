package com.itycu.server.service;

/**
 * Created by fanlinglong on 2018/9/8.
 */
public interface PushService {

    String PushMsg(String title, String msg, Long userid, String url, Long todoid, Long bizid);

}
