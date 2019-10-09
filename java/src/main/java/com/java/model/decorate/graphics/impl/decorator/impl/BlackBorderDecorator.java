package com.java.model.decorate.graphics.impl.decorator.impl;

import com.java.model.decorate.graphics.Component;
import com.java.model.decorate.graphics.impl.decorator.ComponentDecorator;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class BlackBorderDecorator extends ComponentDecorator {

    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        setBlackBorder();
        super.display();
    }

    private void setBlackBorder() {

        System.out.println("为构建添加黑色边框");
    }
}
