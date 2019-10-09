package com.java.model.decorate.excise.impl;

import com.java.model.decorate.excise.Encryption;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class StringEncryption implements Encryption {

    private String str;

    public StringEncryption(String str) {
        this.str = str;
    }

    @Override
    public String getString() {
        return str;
    }

    @Override
    public String encrypt() {
        System.out.println("原始字符串为" + str);
        return str;
    }
}
