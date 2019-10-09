package com.java.model.responsibility.impl;

import com.java.model.responsibility.Approver;
import com.java.model.responsibility.PurchaseRequest;

/**
 * @author Ning
 * @date Create in 2019/4/22
 */
public class President implements Approver {

    private String name;

    public President(String name) {
        this.name = name;
    }

    @Override
    public void handlePurchase(PurchaseRequest purchaseRequest) {
        System.out.println("董事长" + this.name + "审批" + purchaseRequest.toString());
    }

    @Override
    public boolean couldHandle(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getPrice() >= 100000 && purchaseRequest.getPrice() < 500000;
    }
}
