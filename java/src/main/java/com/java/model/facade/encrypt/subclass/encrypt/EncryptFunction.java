package com.java.model.facade.encrypt.subclass.encrypt;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public interface EncryptFunction {

    /**
     * 加密字符串
     * @param str 需要加密的字符串
     * @return 加密结果
     */
    String encrypt(String str);
}
