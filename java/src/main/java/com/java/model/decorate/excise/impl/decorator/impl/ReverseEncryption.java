package com.java.model.decorate.excise.impl.decorator.impl;

import com.java.model.decorate.excise.Encryption;
import com.java.model.decorate.excise.impl.decorator.EncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class ReverseEncryption extends EncryptFunction {


    public ReverseEncryption(Encryption encryption) {
        super(encryption);
    }

    @Override
    public String encrypt() {

        str = super.encrypt();
        return reverseEncrypt(str);
    }

    private String reverseEncrypt(String str) {

        System.out.println("逆向加密");
        return str;
    }
}
