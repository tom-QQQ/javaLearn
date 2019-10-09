package com.java.model.factory.combine.function.impl;

import com.java.model.factory.combine.function.FunctionFactory;

/**
 * @author Ning
 * @date Create in 2019/4/12
 */
public class Study implements FunctionFactory {

    @Override
    public void function() {
        System.out.println("学习");
    }
}
