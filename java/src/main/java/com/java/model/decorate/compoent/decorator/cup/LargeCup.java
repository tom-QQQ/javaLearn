package com.java.model.decorate.compoent.decorator.cup;


import com.java.model.decorate.compoent.AbstractBeverage;
import com.java.model.decorate.compoent.decorator.AbstractDecorator;

/**
 * @author Ning
 * @date Create in 2019/3/28
 */
public class LargeCup extends AbstractDecorator {

    public LargeCup(AbstractBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 大杯";
    }

    @Override
    public double cost() {
        return exactResult(0.2, beverage.cost());
    }
}
