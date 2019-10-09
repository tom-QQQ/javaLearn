package com.java.model.strategy;

import com.java.model.strategy.behavior.fly.impl.FlyRocketPowered;
import com.java.model.strategy.behavior.fly.impl.FlyWithWings;
import com.java.model.strategy.behavior.quack.impl.Quack;
import com.java.model.strategy.behavior.quack.impl.Squeak;
import com.java.model.strategy.impl.MallardDuck;

/**
 * @author Ning
 * @date Create in 2019/3/20
 */
public class TestStrategy {

    public static void main(String[] args) {

        AbstractDuck mallardDuck = new MallardDuck();
        mallardDuck.setFlyBehavior(new FlyWithWings());
        mallardDuck.setQuackBehavior(new Quack());
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.performQuack();
        mallardDuck.swim();

        System.out.println("我要修改飞行动力啦");

        mallardDuck.setFlyBehavior(new FlyRocketPowered());
        mallardDuck.performFly();

        System.out.println("我要修改叫声啦");
        mallardDuck.setQuackBehavior(new Squeak());
        mallardDuck.performQuack();
    }
}
