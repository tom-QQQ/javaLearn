package com.java.model.proxy;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public class AccessValidator {

    public boolean validate(String userName) {

        if ("YangGuo".equals(userName)) {

            System.out.println(userName + " login success");
            return true;

        } else {
            System.out.println("user " + userName + " permission denied");
            return false;
        }
    }
}
