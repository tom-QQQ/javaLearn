package com.java.model.decorate.excise;

import com.java.model.decorate.excise.impl.StringEncryption;
import com.java.model.decorate.excise.impl.decorator.impl.ModEncryption;
import com.java.model.decorate.excise.impl.decorator.impl.MoveEncryption;
import com.java.model.decorate.excise.impl.decorator.impl.ReverseEncryption;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class EncryptionTest {

    public static void main(String[] args) {

        Encryption stringEncryption = new StringEncryption("5555");
        Encryption moveEncryption = new MoveEncryption(stringEncryption);
        Encryption reverseEncryption = new ReverseEncryption(moveEncryption);
        Encryption getReverseEncryption = new ModEncryption(reverseEncryption);

        getReverseEncryption.encrypt();

    }
}
