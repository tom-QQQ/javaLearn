package com.java.model.decorate.function.impl;

import com.java.model.decorate.function.Functions;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class NewFunctions implements Functions {

    private Functions functions;

    public NewFunctions(Functions functions) {
        this.functions = functions;
    }

    @Override
    public void add() {
        functions.add();
    }

    @Override
    public void find() {
        functions.find();
    }

    @Override
    public void delete() {
        functions.delete();
    }
}
