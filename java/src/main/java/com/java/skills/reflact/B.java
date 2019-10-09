package com.java.skills.reflact;

/**
 * @author Ning
 * @date Create in 2019/4/12
 */
public class B {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private B() {
        this(0);
    }

    public B(Integer value) {
        this.value = value;
    }
}
