package com.java.skills;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合相关的技巧
 * @author Ning
 * @date Create in 2019/1/7
 */
public class Collections {

    public static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 在泛型为String的集合中添加Integer类型数据
     * 泛型仅在编译时有效, 由于JVM的泛型擦除机制, 运行时没有泛型信息, 因此可以使用反射将不同于指定泛型的数据插入到集合中
     * @throws Exception NoSuchMethodException
     */
    public static void stringTypeAddInteger() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("String");

        Class c = list.getClass();
        Method listAdd = c.getMethod("add", Object.class);

        listAdd.invoke(list, 20);
        System.out.println(list);

    }

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>(4);
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("4", 1);

    }


}
