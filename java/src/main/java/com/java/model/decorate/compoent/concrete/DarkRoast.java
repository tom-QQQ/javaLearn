package com.java.model.decorate.compoent.concrete;


import com.java.model.decorate.compoent.AbstractBeverage;

/**
 * 被装饰者
 * @author Ning
 * @date Create in 2019/3/28
 */
public class DarkRoast extends AbstractBeverage {

    public DarkRoast() {
        super.description = "深焙咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
