package com.java.model.decorate.compoent;

import java.math.BigDecimal;

/**
 * 饮料抽象类, 子类包括咖啡, 配料和杯子大小
 * @author Ning
 * @date Create in 2019/3/28
 */
public abstract class AbstractBeverage {

    protected String description = "未知饮料";

    public String getDescription() {
        return description;
    }

    /**
     * 价格
     * @return 价格
     */
    public abstract double cost();

    protected double exactResult(double currentPrice, double previousPrice) {

        return BigDecimal.valueOf(currentPrice).add(BigDecimal.valueOf(previousPrice)).doubleValue();
    }
}
