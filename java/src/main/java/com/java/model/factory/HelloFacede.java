package com.java.model.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 持有一个BeanFactoryAware实例对象, 通过HelloFacede实例对象的方法来屏蔽底层BeanFactoryAware实例的实现细节
 *
 * @author Ning
 * @date Create in 2019/1/4
 */
@Component
public class HelloFacede {

    @Autowired
    HelloBeanFactory helloBeanFactory;

    public String sayHello(String beanName, String name) {
        return helloBeanFactory.execute(beanName, name);
    }
}
