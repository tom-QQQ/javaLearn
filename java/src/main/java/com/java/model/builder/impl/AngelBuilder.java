package com.java.model.builder.impl;

import com.java.model.builder.Actor;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class AngelBuilder extends Actor.AbstractBuilder {

    @Override
    public void buildType() {
        actor.setType("天使");
    }

    @Override
    public void buildSex() {
        actor.setSex("女");
    }

    @Override
    public void buildFace() {
        actor.setFace("漂亮");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("白裙");
    }

    @Override
    public void buildHairStyle() {
        actor.setHairStyle("披肩长发");
    }
}
