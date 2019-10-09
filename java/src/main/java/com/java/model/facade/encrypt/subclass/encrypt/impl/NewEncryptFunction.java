package com.java.model.facade.encrypt.subclass.encrypt.impl;

import com.java.model.facade.encrypt.subclass.encrypt.EncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class NewEncryptFunction implements EncryptFunction {

    @Override
    public String encrypt(String str) {


        StringBuilder result = new StringBuilder();

        int key = 10;

        char[] chars = str.toCharArray();

        for (char ch : chars) {
            int temp = (int) ch;

            //小写字母移位
            if (ch >= 'a' && ch <= 'z') {
                temp += key % 26;
                if (temp > 122) {
                    temp -= 26;
                }
                if (temp < 97) {
                    temp += 26;
                }
            }
            //大写字母移位
            if (ch >= 'A' && ch <= 'Z') {
                temp += key % 26;
                if (temp > 90) {
                    temp -= 26;
                }
                if (temp < 65) {
                    temp += 26;
                }
            }

            result.append((char) temp);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EncryptFunction newEncryptFunction = new NewEncryptFunction();
        System.out.println(newEncryptFunction.encrypt("77sdfsaf"));

    }
}
