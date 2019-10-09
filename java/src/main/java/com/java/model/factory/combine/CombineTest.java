package com.java.model.factory.combine;

import com.java.model.factory.combine.function.impl.Play;
import com.java.model.factory.combine.function.impl.Study;
import com.java.model.factory.combine.impl.HappyThings;
import com.java.model.factory.combine.impl.ResistThings;

/**
 * 本想结合抽象工厂和工厂方法, 实际结合了抽象工厂和策略模式
 * @author Ning
 * @date Create in 2019/4/12
 */
public class CombineTest {

    public static void main(String[] args) {

        Factory happyThings = new HappyThings(new Study());
        happyThings.function();
        happyThings.createProperty().printProperty();

        Factory resistThings = new ResistThings(new Play());
        resistThings.function();
        resistThings.createProperty().printProperty();
    }
}
