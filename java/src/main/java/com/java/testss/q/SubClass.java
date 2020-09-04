package com.java.testss.q;


import com.java.testss.p.Super;

/**
 * @author Ning
 * @date Create in 2019/10/19
 */
public class SubClass extends Super {

    private void method() {

        // 子类可以访问
        super.protectedMethod();
    }
}
