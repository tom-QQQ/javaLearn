package com.java.testss.q;

import com.java.testss.p.Super;

/**
 * @author Ning
 * @date Create in 2019/10/19
 */
public class ModifierTest {

    public static void main(String[] args) {

        SubClass subClass = new SubClass();
        // 无法调用Object类protected修饰的clone()方法
//        subClass.clone();
        // 不同包, 不是子类不能访问
//        subClass.protectedMethod();

        SubClassProtected subClassProtected = new SubClassProtected();
        // 无法调用父类的protected修饰的clone()方法
//        subClassProtected.clone();

        Super s = new Super();
        // 不同包, 不是子类不能访问
//        s.protectedMethod();
    }
}
