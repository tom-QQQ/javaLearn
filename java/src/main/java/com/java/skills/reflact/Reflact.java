package com.java.skills.reflact;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ning
 * @date Create in 2019/4/13
 */
public class Reflact {

    public static void main(String[] args) {

        B b = new B(1);

        long startTime = System.currentTimeMillis();

        B copyB = copyObjectByReflex(b, B.class);
        if (copyB != null) {
            System.out.println(copyB.getValue());
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * 使用反射复制对象, 效率很接近于定制化复制对象的方法, 远高于序列化对象后再重新构建对象的方法,
     * 注意: 需要复制的对象的类必须要有无参数构造器; 需要复制字段对应的getter和setter方法必须有public访问权限, 对象中不能有引用类型对象
     * @param t 需要复制的对象
     * @param tClass 需要复制的对象的类
     * @param <T> 对象类型
     * @return 复制结果
     */
    public static <T> T copyObjectByReflex(T t, Class<T> tClass) {

        try {

            // 该方法不能使用私有无参数构造器创建对象
//        T t1 = tClass.newInstance();

            // 获得当前类的无参数构造器
            Constructor<T> constructor =  tClass.getDeclaredConstructor();
            // 设置可能不能访问的构造器可访问
            constructor.setAccessible(true);
            T result = constructor.newInstance();

            // 获取当前类的和父类以及间接父类的全部的访问权限为public的非抽象方法, 如果有方法名与父类相同, 则只会有最底层的方法
            Method[] methods = tClass.getMethods();

            // 去掉9个Object的方法后除以2
            Map<String, Object> valueMap = new HashMap<>(methods.length - 9);

            Object object;

            for (Method method : methods) {

                String methodName = method.getName();
                if (methodName.contains("get") && !"getClass".equals(methodName)) {
                    object = method.invoke(t);
                    if (object != null) {
                        valueMap.put("set" + methodName.substring(3), object);
                    }
                }
            }

            for (Method method : methods) {

                String methodName = method.getName();

                if (methodName.contains("set") && valueMap.containsKey(methodName)) {
                    Object value = valueMap.get(methodName);
                    if (value != null) {
                        method.invoke(result, value);
                    }
                }
            }

            return result;


        } catch (Exception e) {

            // 构造器(无参数)不存在异常, 无权限访问(不会出现)异常
            e.printStackTrace();
            return null;
        }
    }
}
