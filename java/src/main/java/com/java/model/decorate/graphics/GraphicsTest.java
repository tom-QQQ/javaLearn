package com.java.model.decorate.graphics;

import com.java.model.decorate.graphics.component.impl.WindowsComponent;
import com.java.model.decorate.graphics.impl.decorator.impl.BlackBorderDecorator;
import com.java.model.decorate.graphics.impl.decorator.impl.ScrollBarDecorator;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class GraphicsTest {

    public static void main(String[] args) {

        Component windowsComponent = new WindowsComponent();
        Component blackBorderDecorator = new BlackBorderDecorator(windowsComponent);
        Component barDecorator = new ScrollBarDecorator(blackBorderDecorator);

        barDecorator.display();
    }

}
