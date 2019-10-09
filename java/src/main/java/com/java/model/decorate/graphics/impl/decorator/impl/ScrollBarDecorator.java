package com.java.model.decorate.graphics.impl.decorator.impl;

import com.java.model.decorate.graphics.Component;
import com.java.model.decorate.graphics.impl.decorator.ComponentDecorator;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class ScrollBarDecorator extends ComponentDecorator {


    public ScrollBarDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setScrollBar();
        super.display();
    }

    private void setScrollBar() {
        System.out.println("为构件增加滚动条");
    }
}
