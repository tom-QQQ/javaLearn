package com.java.skills.serializable;

import java.io.*;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class SerializableObject implements Serializable {

    private ReferenceObject referenceObject;

    private Integer length;
    private String name;

    public ReferenceObject getReferenceObject() {
        return referenceObject;
    }

    public void setReferenceObject(ReferenceObject referenceObject) {
        this.referenceObject = referenceObject;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SerializableObject deepClone() throws IOException, ClassNotFoundException, OptionalDataException {

        // 将对象写入流中
        ByteArrayOutputStream bao = new  ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        // 将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (SerializableObject) ois.readObject();
    }
}
