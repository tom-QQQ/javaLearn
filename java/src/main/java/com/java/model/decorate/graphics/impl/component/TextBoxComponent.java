package com.java.model.decorate.graphics.component.impl;

import com.java.model.decorate.graphics.Component;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class TextBoxComponent implements Component {

    @Override
    public void display() {
        System.out.println("显示文本框");
    }
}
