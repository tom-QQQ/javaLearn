package com.java.model.decorate.compoent.decorator.cup;


import com.java.model.decorate.compoent.AbstractBeverage;
import com.java.model.decorate.compoent.decorator.AbstractDecorator;

/**
 * @author Ning
 * @date Create in 2019/3/28
 */
public class LittleCup extends AbstractDecorator {

    public LittleCup(AbstractBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "小杯";
    }

    @Override
    public double cost() {
        return exactResult(0.10, beverage.cost());
    }
}
