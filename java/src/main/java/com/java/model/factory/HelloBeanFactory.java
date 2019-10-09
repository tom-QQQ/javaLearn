package com.java.model.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 实现BeanFactoryAware, 让当前bean本身具有beanFactory能力
 *
 * 实现BeanFactoryAware接口的bean可以直接访问Spring容器, 被容器创建之后, 它会拥有一个指向Spring容器的引用,
 * 可以利用该bean根据传入的动态参数获取被Spring工厂加载的bean
 * @author Ning
 * @date Create in 2019/1/4
 */
@Component
public class HelloBeanFactory {

    @Autowired
    private BeanFactory beanFactory;

    /**
     * ApplicationContext也可以用于获取bean, 是BeanFactory的间接子类, 增加了一些方法, 但是如果只需要获取bean, 使用BeanFactory足以
     */
    @Autowired
    private ApplicationContext applicationContext;

    public String executeWithApplicationContext(String beanName, String name) {
        HelloService helloService = (HelloService) applicationContext.getBean(beanName);
        return helloService.sayHello(name);
    }

    /**
     * 提供一个execute方法来实现不同业务实现类的调度器方案
     * @param beanName bean名称
     * @param name 名字
     */
    public String execute(String beanName, String name) {
        HelloService helloService = (HelloService) beanFactory.getBean(beanName);
        return helloService.sayHello(name);
    }
}
