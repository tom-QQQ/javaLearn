package com.java.model.decorate.function.impl.function;

import com.java.model.decorate.function.Functions;
import com.java.model.decorate.function.impl.NewFunctions;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class AddFunction extends NewFunctions {

    public AddFunction(Functions functions) {
        super(functions);
    }

    @Override
    public void add() {
        addFunction();
    }

    private void addFunction() {
        System.out.println("实现了addFunction");
    }
}
