package com.spring.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 链式处理, 一般用于将复杂的数据处理进行分步处理, 或处理大体相同但因个别属性不同而需要进行不同处理的数据(如处理rabbitMQ接收到的消息)
 * @author Ning
 * @date Create in 2019/3/3
 */
@Component
public class HandlerChain {

    /**
     * 这个会自动将实现Handler接口的全部被@Component注解的类根据@Order设置的顺序(如果有)添加到该list中
     */
    @Autowired
    private List<Handler> handlerList;

    /**
     * 数据多步骤处理/根据属性处理需要调用的方法, 链式处理中唯一需要暴露给外界的
     * @param jsonObject 数据参数, 如果只处理一次, 则不需要构造ChainParam对象, 需要处理多次时, 则在内部构造ChainParam对象, 不暴露给调用方
     */
    public void handler(JSONObject jsonObject) {

        ChainParam chainParam = new ChainParam(jsonObject);

        for (Handler handler : handlerList) {
            boolean ifNeedContinue = handler.progress(chainParam);

            // 判断是否需要继续处理, 返回false时处理流程结束
            if (!ifNeedContinue) {
                return;
            }
        }
    }
}
