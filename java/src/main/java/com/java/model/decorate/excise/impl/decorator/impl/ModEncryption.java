package com.java.model.decorate.excise.impl.decorator.impl;

import com.java.model.decorate.excise.Encryption;
import com.java.model.decorate.excise.impl.decorator.EncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class ModEncryption extends EncryptFunction {

    public ModEncryption(Encryption encryption) {
        super(encryption);
    }

    @Override
    public String encrypt() {
        str = super.encrypt();
        return modEncrypt(str);
    }

    private String modEncrypt(String str) {
        System.out.println("求模加密");
        return str;
    }
}
