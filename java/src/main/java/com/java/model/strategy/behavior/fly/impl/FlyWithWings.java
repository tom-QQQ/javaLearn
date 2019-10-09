package com.java.model.strategy.behavior.fly.impl;


import com.java.model.strategy.behavior.fly.FlyBehavior;

/**
 * @author Ning
 * @date Create in 2019/3/20
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("用翅膀飞行");
    }
}
