package com.java.model.builder.impl;

import com.java.model.builder.Actor;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class HeroBuilder extends Actor.AbstractBuilder {

    @Override
    public void buildType() {
        actor.setType("英雄");
    }

    @Override
    public void buildSex() {
        actor.setSex("男");
    }

    @Override
    public void buildFace() {
        actor.setFace("英俊");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("盔甲");
    }

    @Override
    public void buildHairStyle() {
        actor.setHairStyle("飘逸");
    }
}
