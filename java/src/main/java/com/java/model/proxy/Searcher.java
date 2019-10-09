package com.java.model.proxy;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public interface Searcher {

    /**
     * 查询
     * @param userName 用户名
     * @param keyWord 查询关键字
     * @return 查询结果
     */
    String doSearch(String userName, String keyWord);
}
