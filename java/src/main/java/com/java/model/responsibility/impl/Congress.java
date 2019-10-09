package com.java.model.responsibility.impl;

import com.java.model.responsibility.Approver;
import com.java.model.responsibility.PurchaseRequest;

/**
 * @author Ning
 * @date Create in 2019/4/22
 */
public class Congress implements Approver {


    @Override
    public void handlePurchase(PurchaseRequest purchaseRequest) {
        System.out.println("董事会审批" + purchaseRequest.toString());
    }

    @Override
    public boolean couldHandle(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getPrice() >= 500000;
    }
}
