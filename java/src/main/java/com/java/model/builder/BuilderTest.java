package com.java.model.builder;

import com.java.model.builder.impl.AngelBuilder;
import com.java.model.builder.impl.DevilBuilder;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class BuilderTest {

    public static void main(String[] args) {

        Actor.AbstractBuilder actorBuilder = new DevilBuilder();
        Actor actor = actorBuilder.construct(actorBuilder);
        System.out.println("");

    }
}
