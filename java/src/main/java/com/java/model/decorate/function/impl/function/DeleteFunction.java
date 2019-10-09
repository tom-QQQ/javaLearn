package com.java.model.decorate.function.impl.function;

import com.java.model.decorate.function.Functions;
import com.java.model.decorate.function.impl.NewFunctions;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class DeleteFunction extends NewFunctions {

    public DeleteFunction(Functions functions) {
        super(functions);
    }

    @Override
    public void delete() {
        deleteFunction();
    }

    private void deleteFunction() {

        System.out.println("实现了deleteFunction");
    }
}
