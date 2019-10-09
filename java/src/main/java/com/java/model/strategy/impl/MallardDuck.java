package com.java.model.strategy.impl;


import com.java.model.strategy.AbstractDuck;

/**
 * @author Ning
 * @date Create in 2019/3/20
 */
public class MallardDuck extends AbstractDuck {

    @Override
    public void display() {
        System.out.println("我是一只绿头鸭");
    }
}
