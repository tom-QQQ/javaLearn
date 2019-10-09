package com.java.skills.serializable;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class SuperObject implements Serializable {

    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
