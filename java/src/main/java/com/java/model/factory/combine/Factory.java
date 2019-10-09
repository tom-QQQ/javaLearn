package com.java.model.factory.combine;

import com.java.model.factory.combine.function.FunctionFactory;
import com.java.model.factory.combine.property.PropertyFactory;

/**
 * @author Ning
 * @date Create in 2019/4/12
 */
public interface Factory extends FunctionFactory {

    PropertyFactory createProperty();
}
