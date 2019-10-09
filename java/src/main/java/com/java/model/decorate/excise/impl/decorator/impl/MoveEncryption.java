package com.java.model.decorate.excise.impl.decorator.impl;

import com.java.model.decorate.excise.Encryption;
import com.java.model.decorate.excise.impl.decorator.EncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class MoveEncryption extends EncryptFunction {

    public MoveEncryption(Encryption encryption) {
        super(encryption);
    }

    @Override
    public String encrypt() {

        str = super.encrypt();
        return moveEncrypt(str);
    }

    private String moveEncrypt(String str) {

        System.out.println("位移加密");
        return str;
    }
}
