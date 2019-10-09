package com.java.model.proxy;

import com.java.model.proxy.impl.ProxySearcher;

/**
 * 该示例是为已有功能使用代理类新增一个身份验证功能, 不需要修改已有代码
 * @author Ning
 * @date Create in 2019/4/19
 */
public class ProxyTest {

    public static void main(String[] args) {


        Searcher proxySearcher = new ProxySearcher();
        String searchContent = proxySearcher.doSearch("YangGuo", "xxxxx");
        System.out.println("Search content is: " + searchContent);

//        System.out.println("Search content is: " + proxySearcher.doSearch("Yang", "xxxx"));
    }
}
