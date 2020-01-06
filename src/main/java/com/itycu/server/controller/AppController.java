package com.itycu.server.controller;

import com.itycu.server.dao.ParamsDao;
import com.itycu.server.model.Params;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    private ParamsDao paramsDao;

    @GetMapping("/appVersion")
    @ApiOperation(value = "获取app最新版本")
    public Map appVersion() {
        Map map = new HashMap<>();
        Params byName = paramsDao.getByName(" APP版本信息");
        if (byName == null){
            map.put("status",0);
            map.put("msg","请联系管理员，系统参数中没有APP版本信息");
        }else{
            if (byName.getVal1() == null || "".equals(byName.getVal1())){
                map.put("status",0);
                map.put("msg","请联系管理员，系统参数APP版本信息中没有录入版本");
            }else{
                if (byName.getVal3() == null || "".equals(byName.getVal3())){
                    map.put("status",0);
                    map.put("msg","请联系管理员，系统参数APP版本信息中没有录入下载地址");
                }else{
                    map.put("status",1);
                    map.put("version",byName.getVal1());
                    map.put("content",byName.getVal2());
                    map.put("url",byName.getVal3());
                }
            }
        }
        return map;
    }
}
