package com.java.model.factory.impl;

import com.java.model.factory.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author Ning
 * @date Create in 2019/1/4
 * '@Component'设置Spring容器中component的名称
 */
@Component("ChineseHelloService")
public class ChineseHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "你好, " + name;
    }
}
