package com.java.model.factory.combine.property.impl;

import com.java.model.factory.combine.property.PropertyFactory;

/**
 * @author Ning
 * @date Create in 2019/4/12
 */
public class Happy implements PropertyFactory {

    @Override
    public void printProperty() {
        System.out.println("开心");
    }
}
