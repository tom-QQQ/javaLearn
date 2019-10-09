package com.java.model.facade.encrypt;

import com.java.model.facade.encrypt.subclass.encrypt.EncryptFunction;
import com.java.model.facade.encrypt.subclass.FileRead;
import com.java.model.facade.encrypt.subclass.FileWrite;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class EncryptFacade {

    public void encryptString(EncryptFunction encryptFunction) {

        FileRead fileRead = new FileRead();
        FileWrite fileWrite = new FileWrite();

        String str = fileRead.read("a.txt");
        str = encryptFunction.encrypt(str);
        fileWrite.writeFile(str);

    }
}
