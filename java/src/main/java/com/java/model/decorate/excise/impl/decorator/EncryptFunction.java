package com.java.model.decorate.excise.impl.decorator;

import com.java.model.decorate.excise.Encryption;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class EncryptFunction implements Encryption {

    private Encryption encryption;
    protected String str;

    public EncryptFunction(Encryption encryption) {
        this.encryption = encryption;
        str = encryption.getString();
    }

    @Override
    public String getString() {
        return str;
    }

    @Override
    public String encrypt() {

        return encryption.encrypt();
    }
}
