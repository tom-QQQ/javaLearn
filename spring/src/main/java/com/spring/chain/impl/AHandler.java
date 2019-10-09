package com.spring.chain.impl;

import com.spring.chain.ChainParam;
import com.spring.chain.Handler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Ning
 * @date Create in 2019/3/3
 * 处理步骤的具体实现类, 一定要加@Component注解, 否则会造成数据处理不完整
 * '@Order'用于指定处理顺序, 如果由于数据处理较为复杂, 则拆分为多个步骤后需要使用该注解来指定处理顺序
 */
@Component
@Order(1)
public class AHandler implements Handler {

    @Override
    public boolean progress(ChainParam chainParam) {

        // 数据处理具体逻辑

        return false;
    }
}
