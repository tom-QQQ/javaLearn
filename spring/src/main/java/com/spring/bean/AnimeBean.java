package com.spring.bean;

import lombok.Data;

/**
 * @author Ning
 * @date Create in 2018/12/29
 * '@Data'会重写toString(), equals(), hashCode()方法
 */
public class AnimeBean {

    private String name = "青春猪头少年不会遇到兔女郎学姐";
    private String period = "2018";

    public void start() {
        System.out.println("AnimeBean被创建");
    }

    public void destroy() {
        System.out.println("AnimeBean被销毁");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}
