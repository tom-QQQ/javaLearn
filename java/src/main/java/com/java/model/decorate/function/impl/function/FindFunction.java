package com.java.model.decorate.function.impl.function;

import com.java.model.decorate.function.Functions;
import com.java.model.decorate.function.impl.NewFunctions;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class FindFunction extends NewFunctions {

    public FindFunction(Functions functions) {
        super(functions);
    }

    @Override
    public void find() {
        findFunction();
    }

    private void findFunction() {
        System.out.println("实现了findFunction");
    }
}
