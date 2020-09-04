package com.java.testss.p;

/**
 * @author Ning
 * @date Create in 2019/10/19
 */
public class SuperProtected {

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Super s = new Super();
        // 同一个包可以访问
        s.protectedMethod();

        try {
            return super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
