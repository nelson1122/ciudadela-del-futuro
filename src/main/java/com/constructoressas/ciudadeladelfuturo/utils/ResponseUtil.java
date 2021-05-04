package com.constructoressas.ciudadeladelfuturo.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> mapOK(Object obj) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", true);
        modelMap.put("data", obj);
        return modelMap;
    }

    public static Map<String, Object> mapError(String msg) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", false);
        modelMap.put("message", msg);
        return modelMap;
    }
}
