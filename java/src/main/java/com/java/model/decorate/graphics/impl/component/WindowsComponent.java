package com.java.model.decorate.graphics.component.impl;

import com.java.model.decorate.graphics.Component;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class WindowsComponent implements Component {

    @Override
    public void display() {
        System.out.println("显示窗体");
    }
}
