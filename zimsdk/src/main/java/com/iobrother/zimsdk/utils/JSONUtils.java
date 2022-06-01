package com.iobrother.zimsdk.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JSONUtils {
    public static String toString(Object o) {
        return JSONObject.toJSONString(o);
    }

    public static <T> T toObj(String text, Class<T> clazz) {
        try {
            return JSONObject.parseObject(text, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toArray(String text, Class<T> clazz) {
        try {
            return JSONObject.parseArray(text, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
