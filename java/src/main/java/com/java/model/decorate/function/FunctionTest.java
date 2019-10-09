package com.java.model.decorate.function;

import com.java.model.decorate.function.impl.MainFunction;
import com.java.model.decorate.function.impl.function.AddFunction;
import com.java.model.decorate.function.impl.function.DeleteFunction;
import com.java.model.decorate.function.impl.function.FindFunction;

/**
 * 补充功能
 * @author Ning
 * @date Create in 2019/4/16
 */
public class FunctionTest {

    public static void main(String[] args) {

        Functions mainFunction = new MainFunction();

        mainFunction.add();
        mainFunction.find();
        mainFunction.delete();

        Functions addFunction = new AddFunction(mainFunction);

        addFunction.add();
        addFunction.find();
        addFunction.delete();

        Functions findFunction = new FindFunction(addFunction);
        Functions deleteFunction = new DeleteFunction(findFunction);

        deleteFunction.add();
        deleteFunction.find();
        deleteFunction.delete();

    }
}
