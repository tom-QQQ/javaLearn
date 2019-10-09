package com.java.model.facade.encrypt;

import com.java.model.facade.encrypt.subclass.encrypt.impl.NewEncryptFunction;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class EncryptTest {

    public static void main(String[] args) {

        EncryptFacade encryptFacade = new EncryptFacade();
        encryptFacade.encryptString(new NewEncryptFunction());
    }
}
