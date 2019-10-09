package com.java.model.responsibility.impl;

import com.java.model.responsibility.Approver;
import com.java.model.responsibility.PurchaseRequest;

/**
 * @author Ning
 * @date Create in 2019/4/20
 */
public class VicePresident implements Approver {

    private String name;

    public VicePresident(String name) {
        this.name = name;
    }

    @Override
    public void handlePurchase(PurchaseRequest purchaseRequest) {
        System.out.println("副董事长" + this.name + "审批" + purchaseRequest.toString());
    }

    @Override
    public boolean couldHandle(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getPrice() < 100000 && purchaseRequest.getPrice() >= 50000;
    }
}
