package com.java.model.decorate.compoent.decorator.cup;


import com.java.model.decorate.compoent.AbstractBeverage;
import com.java.model.decorate.compoent.decorator.AbstractDecorator;

/**
 * @author Ning
 * @date Create in 2019/3/28
 */
public class MiddleCup extends AbstractDecorator {

    public MiddleCup(AbstractBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "中杯";
    }

    @Override
    public double cost() {
        return exactResult(0.15, beverage.cost());
    }
}
