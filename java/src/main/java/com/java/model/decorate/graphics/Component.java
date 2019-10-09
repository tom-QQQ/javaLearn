package com.java.model.decorate.graphics;

/**
 * 组件接口, 完全实现类是基本组件, 调用需要实现的方法的类是装饰组件, 适合对数据进行补充或处理, 不适合新增供子类调用的功能
 * @author Ning
 * @date Create in 2019/4/16
 */
public interface Component {

    void display();
}
