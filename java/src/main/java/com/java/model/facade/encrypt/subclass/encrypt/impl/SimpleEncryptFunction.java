package com.java.model.facade.encrypt.subclass.encrypt.impl;

import com.java.model.facade.encrypt.subclass.encrypt.EncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class SimpleEncryptFunction implements EncryptFunction {

    @Override
    public String encrypt(String str) {

        StringBuilder stringBuilder = new StringBuilder();

        char[] chars = str.toCharArray();

        for (char c : chars) {

            stringBuilder.append(String.valueOf(c % 7));
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        EncryptFunction encryptFunction = new SimpleEncryptFunction();
        System.out.println(encryptFunction.encrypt("asffaw"));
    }
}
