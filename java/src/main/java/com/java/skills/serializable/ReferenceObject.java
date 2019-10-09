package com.java.skills.serializable;

import java.io.Serializable;

/**
 * 浅克隆
 * @author Ning
 * @date Create in 2019/4/15
 */
public class ReferenceObject extends SuperObject implements Cloneable, Serializable  {

    private Integer value;
    private Integer size;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public ReferenceObject clone() {

        Object result;

        try {

            result = super.clone();
            return (ReferenceObject) result;

        } catch (CloneNotSupportedException cse) {
            cse.printStackTrace();
            return null;
        }
    }
}
