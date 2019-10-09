package com.java.model.decorate.graphics.impl.decorator;

import com.java.model.decorate.graphics.Component;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class ComponentDecorator implements Component {

    private Component component;

    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
