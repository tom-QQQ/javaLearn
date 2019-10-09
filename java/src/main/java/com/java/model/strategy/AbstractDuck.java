package com.java.model.strategy;

import com.java.model.strategy.behavior.fly.FlyBehavior;
import com.java.model.strategy.behavior.quack.QuackBehavior;

/**
 * 对于可能变化的行为, 使用接口替代, 在具体对象中设置不同的实现类以达到不同的行为
 *
 * 策略模式, 定义算法族, 使之可以互相替换, 令算法的变化独立于使用算法的客户
 * 在实际使用的过程中, 具体的实现类应该构建为Spring的Bean以供不同的实现类使用
 * @author Ning
 * @date Create in 2019/3/20
 */
public abstract class AbstractDuck {

    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    /**
     * 动态设置鸭子的飞行行为
     * @param flyBehavior 飞行行为
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    /**
     * 动态设置鸭子的发出叫声行为
     * @param quackBehavior 发出叫声行为
     */
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    protected void swim() {
        System.out.println("会游泳");
    }

    /**
     * 鸭子的样子
     */
    protected abstract void display();

    protected void performQuack() {
        quackBehavior.quack();
    }

    protected void performFly() {
        flyBehavior.fly();
    }
}
