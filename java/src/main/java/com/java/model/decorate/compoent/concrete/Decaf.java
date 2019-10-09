package com.java.model.decorate.compoent.concrete;


import com.java.model.decorate.compoent.AbstractBeverage;

/**
 * 被装饰者
 * @author Ning
 * @date Create in 2019/3/28
 */
public class Decaf extends AbstractBeverage {

    public Decaf() {
        super.description = "低咖啡因咖啡";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
