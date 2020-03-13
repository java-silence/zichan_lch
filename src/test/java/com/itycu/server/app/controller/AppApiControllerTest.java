package com.itycu.server.app.controller;

import com.itycu.server.model.SysUser;
import com.itycu.server.utils.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppApiControllerTest {


    @Test
    public void getUserInfo() {
        SysUser sysUser = UserUtil.getLoginUser();
        System.out.println(sysUser);
    }
}
