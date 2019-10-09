package com.spring.chain;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Ning
 * @date Create in 2019/3/3
 * 链式处理接口
 */
public interface Handler {

    /**
     * 实现类实现具体处理步骤,
     * 如果数据只需要一次处理,则首先需要判断该步骤是否能处理该数据, 不能时直接返回true, 等待其他步骤处理, 可以处理时在数据处理完毕之后返回false, 该条数据处理完毕;
     * 如果需要多次处理, 则在当前步骤处理完毕后, 如果数据需要继续处理时返回true, 不需要继续处理时返回false
     * 以上返回值和{@link HandlerChain#handler(JSONObject)}方法相对应
     *
     * @param chainParam 需要处理的处理数据
     * @return 是否需要其他步骤继续处理, 需要返回true, 不需要返回false
     */
    boolean progress(ChainParam chainParam);

}
