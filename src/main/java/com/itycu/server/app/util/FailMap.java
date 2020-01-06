package com.itycu.server.app.util;

import java.util.HashMap;
import java.util.Map;

public class FailMap {


    public  static Map<String, Object> createFailMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", "失败");
        map.put("data", null);
        return map;
    }

}
