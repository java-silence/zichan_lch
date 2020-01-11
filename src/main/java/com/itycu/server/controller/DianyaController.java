package com.itycu.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.itycu.server.model.DianYa;
import com.itycu.server.service.DianyaService;
import com.itycu.server.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/dianya")
public class DianyaController {


    @Value("${DIAN_JIAN_CE_HOST}")
    private String DIAN_JIAN_CE_HOST;


    private static Logger logger = LoggerFactory.getLogger(DianyaController.class);

    @Autowired
    private DianyaService dianyaService;


    @PostMapping(value = "/average/{id}")
    public JSONObject getDianYaAverage(@PathVariable("id") int id) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/GetInfoRealTime";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceID", id);
        String dataStr = "dataStr=" + jsonObject.toJSONString();
        String result = HttpClientUtil.sendPost(url, dataStr);
        logger.info("添加温度的列表数据url：===>{}", url);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("添加某个设备的温度数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }



    @PostMapping(value = "/getTemList/{deptID}")
    @ResponseBody
    public JSONObject getListDevice(@PathVariable("deptID") int deptID  ) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/GetListDeviceMaxRealTime";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deptID", deptID);
        String dataStr = "dataStr=" + jsonObject.toJSONString();
        String result = HttpClientUtil.sendPost(url, dataStr);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("添加温度的列表数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }




    @GetMapping(value = "/list/{deptID}")
    @ResponseBody
    public Map<String, Object> getListDevice(@RequestParam(value = "deviceName", required = false) String deviceName,
                                             @RequestParam(value = "offset",defaultValue = "1") int offset,
                                             @PathVariable("deptID") int deptID,
                                             @RequestParam(value = "limit",defaultValue = "20") int limit) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/GetListDevice";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceName", deviceName);
        jsonObject.put("offset", offset);
        jsonObject.put("limit", limit);
        jsonObject.put("deptID", deptID);
        String dataStr = "dataStr=" + jsonObject.toJSONString();
        String result = HttpClientUtil.sendPost(url, dataStr);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("添加格式化列表数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
                json.put("count", json.get("count"));
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }


    @PostMapping(value = "/add")
    public JSONObject addDevice(@RequestBody DianYa dian) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/AddDevice";
        String dataStr = "dataStr=" + JSONObject.toJSONString(dian);
        String result = HttpClientUtil.sendPost(url, dataStr);
        logger.info("添加格式化返回数据：===>{},JSON===={}", result, JSONObject.toJSONString(dian));
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("添加格式化之后数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }


    @PostMapping(value = "/delete/{deviceId}")
    public JSONObject deleteDevice(@PathVariable("deviceId") int deviceId) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/DeleteDevice";
        String dataStr = "DeviceID=" + JSONObject.toJSONString(deviceId);
        String result = HttpClientUtil.sendPost(url, dataStr);
        logger.info("删除返回数据：===>{}", result);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("删除返回数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }


    @PostMapping(value = "/update")
    public JSONObject updateDevice(@RequestBody DianYa dianYa) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/UpdateDevice";
        String dataStr = "dataStr=" + JSONObject.toJSONString(dianYa);
        String result = HttpClientUtil.sendPost(url, dataStr);
        logger.info("更新后返回数据：===>{}", result);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("更新后返回数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }


    @PostMapping(value = "/getById/{deviceId}")
    @ResponseBody
    public JSONObject getById(@PathVariable("deviceId") int deviceId) {
        JSONObject json = new JSONObject();
        String url = DIAN_JIAN_CE_HOST + "/Default/GetInfoDevice";
        // String dataStr = "deviceID=" + JSONObject.toJSONString(deviceId);
        String dataStr = "deviceID=" + deviceId;
        String result = HttpClientUtil.sendPost(url, dataStr);
        logger.info("查询某一个数据返回数据：===>{}", result);
        if (!StringUtils.isEmpty(result)) {
            json = JSONObject.parseObject(result);
            logger.info("查询某一个数据返回数据：===>{}", json);
            if (json.get("result").equals(true)) {
                json.put("code", 0);
            } else {
                json = createFailJson((String) json.get("message"));
            }
        } else {
            json = createFailJson(null);
        }
        return json;
    }


    private JSONObject createFailJson(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 500);
        if (StringUtils.isEmpty(message)) {
            jsonObject.put("message", "操作错误");
        } else {
            jsonObject.put("message", message);
        }
        jsonObject.put("data", "");
        return jsonObject;
    }
}
