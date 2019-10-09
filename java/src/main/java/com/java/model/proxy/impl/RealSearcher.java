package com.java.model.proxy.impl;

import com.java.model.proxy.Searcher;

import java.text.MessageFormat;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public class RealSearcher implements Searcher {

    @Override
    public String doSearch(String userName, String keyWord) {

        System.out.println(MessageFormat.format("User [{0}] use key word [{1}] search information!", userName, keyWord));

        return "Top secret information";
    }
}
