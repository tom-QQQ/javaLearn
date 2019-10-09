package com.java.model.responsibility.impl;

import com.java.model.responsibility.Approver;
import com.java.model.responsibility.PurchaseRequest;

/**
 * @author Ning
 * @date Create in 2019/4/20
 */
public class Director implements Approver {

    private String name;

    public Director(String name) {
        this.name = name;
    }

    @Override
    public void handlePurchase(PurchaseRequest purchaseRequest) {
        System.out.println("主任" + this.name + "审批" + purchaseRequest.toString());
    }

    @Override
    public boolean couldHandle(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getPrice() < 50000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
