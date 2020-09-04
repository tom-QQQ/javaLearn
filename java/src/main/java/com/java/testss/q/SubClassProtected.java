package com.java.testss.q;

import com.java.testss.p.Super;
import com.java.testss.p.SuperProtected;

/**
 * @author Ning
 * @date Create in 2019/10/19
 */
public class SubClassProtected extends SuperProtected {

    public void test() throws Exception {

        SubClassProtected subClassProtected = new SubClassProtected();
        // 可以调用父类protected修饰的clone()方法
        subClassProtected.clone();

        SuperProtected superProtected = new SubClassProtected();
//        superProtected.clone();

    }
}
