package com.java.model.decorate.compoent;

import com.java.model.decorate.compoent.concrete.DarkRoast;
import com.java.model.decorate.compoent.concrete.Espresso;
import com.java.model.decorate.compoent.concrete.HouseBlend;
import com.java.model.decorate.compoent.decorator.condiment.Mocha;
import com.java.model.decorate.compoent.decorator.condiment.Soy;
import com.java.model.decorate.compoent.decorator.condiment.Whip;
import com.java.model.decorate.compoent.decorator.cup.LargeCup;


/**
 * @author Ning
 * @date Create in 2019/3/28
 */
public class Test {

    public static void main(String[] args) {

        AbstractBeverage espresso = new Espresso();

        System.out.println(espresso.getDescription() + "价格为: " + espresso.cost());

        AbstractBeverage darkRoast = new DarkRoast();
        darkRoast = new LargeCup(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Whip(darkRoast);
        // 获取信息时实际上是在迭代处理
        System.out.println(darkRoast.getDescription() + "价格为: " + darkRoast.cost());

        AbstractBeverage houseBlend = new HouseBlend();
        houseBlend = new Soy(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + "价格为: " + houseBlend.cost());
    }
}
