package com.java.model.decorate.compoent.concrete;


import com.java.model.decorate.compoent.AbstractBeverage;

/**
 * 被装饰者
 * @author Ning
 * @date Create in 2019/3/28
 */
public class HouseBlend extends AbstractBeverage {

    public HouseBlend() {
        super.description = "混合咖啡";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
