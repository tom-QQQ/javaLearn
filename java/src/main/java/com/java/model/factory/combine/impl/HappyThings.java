package com.java.model.factory.combine.impl;

import com.java.model.factory.combine.Factory;
import com.java.model.factory.combine.function.FunctionFactory;
import com.java.model.factory.combine.property.PropertyFactory;
import com.java.model.factory.combine.property.impl.Happy;

/**
 * @author Ning
 * @date Create in 2019/4/12
 */
public class HappyThings implements Factory {

    private FunctionFactory functionFactory;

    public HappyThings(FunctionFactory functionFactory) {
        this.functionFactory = functionFactory;
    }

    @Override
    public void function() {
        this.functionFactory.function();
    }

    @Override
    public PropertyFactory createProperty() {
        return new Happy();
    }
}
