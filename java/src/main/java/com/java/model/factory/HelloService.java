package com.java.model.factory;

/**
 * @author Ning
 * @date Create in 2019/1/4
 */
public interface HelloService {

    /**
     * 说你好
     * @param name 名字
     * @return 不同语言说你好
     */
    String sayHello(String name);
}
