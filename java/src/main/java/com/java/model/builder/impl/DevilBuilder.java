package com.java.model.builder.impl;

import com.java.model.builder.Actor;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class DevilBuilder extends Actor.AbstractBuilder {

    @Override
    public void buildType() {
        actor.setType("恶魔");
    }

    @Override
    public void buildSex() {
        actor.setSex("妖");
    }

    @Override
    public void buildFace() {
        actor.setFace("丑陋");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("黑衣");
    }

    @Override
    public void buildHairStyle() {
        actor.setHairStyle("光头");
    }

    /**
     * 覆盖抽象类方法, 恶魔没有头发
     * @return 是光头
     */
    @Override
    public boolean isBareheaded() {
        return true;
    }
}
