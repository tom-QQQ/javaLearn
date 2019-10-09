package com.spring.chain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author Ning
 * @date Create in 2019/3/3
 * 链式处理参数, 如果数据仅需要处理一次, 可以不用该参数, 使用一个简单参数代替, 如JSONObject
 * 如果需要该参数, 则不应将该参数暴露给数据处理调用方, 应使用构造器构造该参数后使用
 */
@Data
public class ChainParam {

    private JSONObject data;

    /**
     * 处理构造器级别, 不让除HandlerChain和Handler以及实现Handler接口之外的其他方法接触到, 可能存在的其他getter/setter方法也应有相同的访问权限
     * @param jsonObject 参数, 原始的需要处理的数据, 可能为其他格式, 这里只是示例
     */
    ChainParam(JSONObject jsonObject) {
        this.data = jsonObject;
    }
}
