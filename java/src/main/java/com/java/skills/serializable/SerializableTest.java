package com.java.skills.serializable;

import com.alibaba.fastjson.JSONObject;
import com.java.skills.reflact.Reflact;

/**
 * 总结:
 * 当前类没有引用对象没有父类时, 使用实现Cloneable接口
 * 没有引用对象, 有父类, 父类以及间接父类没有引用对象时使用反射
 * 既有引用对象又有父类时使用序列化, 使用序列化时, 父类, 引用类及其父类都有实现Serializable接口, 如果引用类中包含引用类, 只能先复制内部引用类, 再复制外部类了
 * @author Ning
 * @date Create in 2019/4/15
 */
public class SerializableTest {

    public static void main(String[] args) {


        ReferenceObject referenceObject = new ReferenceObject();
        referenceObject.setSize(20);
        referenceObject.setWeight(30);

        SerializableObject serializableObject = new SerializableObject();
        serializableObject.setReferenceObject(referenceObject);
        serializableObject.setLength(15);
        serializableObject.setName("nnn");

//        long startTime = System.currentTimeMillis();
//        SerializableObject copyObjectByReflact =  Reflact.copyObjectByReflex(serializableObject, SerializableObject.class);
//        System.out.println("反射拷贝耗时" + (System.currentTimeMillis() - startTime));

        long startTime = System.currentTimeMillis();
        SerializableObject copyObjectBySerializable = null;
        try {
            copyObjectBySerializable = serializableObject.deepClone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("序列化耗时" + (System.currentTimeMillis() - startTime));

        if (copyObjectBySerializable != null) {

            System.out.println(copyObjectBySerializable.getReferenceObject().getWeight());
            referenceObject.setWeight(0);
            System.out.println(copyObjectBySerializable.getReferenceObject().getWeight());
        }

//        startTime = System.currentTimeMillis();
//        SerializableObject copyByJSON = JSONObject.parseObject(JSONObject.toJSONString(serializableObject), SerializableObject.class);
//        System.out.println("JSON复制耗时" + (System.currentTimeMillis() - startTime));



    }
}
