package com.java.model.proxy.impl;

import com.java.model.proxy.AccessValidator;
import com.java.model.proxy.Logger;
import com.java.model.proxy.Searcher;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public class ProxySearcher implements Searcher {

    private RealSearcher realSearcher = new RealSearcher();
    private AccessValidator accessValidator = new AccessValidator();
    private Logger logger = new Logger();

    @Override
    public String doSearch(String userName, String keyWord) {

        if (!accessValidator.validate(userName)) {
            return "Without permission.";
        }

        logger.log(userName + " searched information.");
        return realSearcher.doSearch(userName, keyWord);
    }
}
