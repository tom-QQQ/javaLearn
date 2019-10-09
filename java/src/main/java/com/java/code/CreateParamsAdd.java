package com.java.code;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ning
 * @date Create in 2019/4/30
 */
public class CreateParamsAdd {

    public static void createParamsAdd(String mapName, String... paramsName) {

        for (String name : paramsName) {

            System.out.println("if (" + name + " != null) {");
            System.out.println(mapName + ".put(\"" + name + "\", " + name + ");" + "\n" + "}");
            System.out.println("");
        }
    }

    public static <T> void putNotNullValue(Map<String, Object> map, T t, String name){

        if (t != null) {
            map.put(name, t);
        }
    }

    /**
     * 使用putNotNullValue方法将非空对象放入map中
     * @param mapName
     * @param paramsName
     */
    public static void putValueWithMethod(String mapName, String... paramsName) {

        for (String name : paramsName) {

            System.out.println("putNotNullValue(" + mapName + ", " + name + ", " + "\"" + name + "\"" + ");");
        }

    }

    public static void main(String[] args) {

        putValueWithMethod("queryParam", "pageNum", "pageSize", "systemId", "serialId", "name", "type", "onlineStatus", "toolStatus", "model", "useGroupId",
                "entryStartTime", "entryEndTime", "minEnterDays", "maxEnterDays", "useStartTime", "useEndTime");
    }
}
