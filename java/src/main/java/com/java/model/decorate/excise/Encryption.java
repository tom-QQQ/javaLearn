package com.java.model.decorate.excise;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public interface Encryption {

    /**
     * 获取字符串
     * @return 需要加盟的字符串
     */
    String getString();

    /**
     * 加密方法
     * @return 加密结果
     */
    String encrypt();
}
