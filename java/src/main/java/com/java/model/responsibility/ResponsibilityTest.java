package com.java.model.responsibility;

import com.java.model.responsibility.impl.Congress;
import com.java.model.responsibility.impl.Director;
import com.java.model.responsibility.impl.President;
import com.java.model.responsibility.impl.VicePresident;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/4/22
 */
public class ResponsibilityTest {

    public static void main(String[] args) {

        List<Approver> approverList = new ArrayList<>();
        approverList.add(new Congress());
        approverList.add(new Director("张无忌"));
        approverList.add(new President("郭靖"));
        approverList.add(new VicePresident("杨过"));

        PurchaseRequest purchaseRequest1 = new PurchaseRequest(45000,10001,"购买倚天剑");
        handlePurchaseRequest(purchaseRequest1, approverList);

        PurchaseRequest purchaseRequest2 = new PurchaseRequest(60000,10002,"购买《葵花宝典》");
        handlePurchaseRequest(purchaseRequest2, approverList);

        PurchaseRequest purchaseRequest3 = new PurchaseRequest(160000,10003,"购买《金刚经》");
        handlePurchaseRequest(purchaseRequest3, approverList);

        PurchaseRequest purchaseRequest4 = new PurchaseRequest(800000,10004,"购买桃花岛");
        handlePurchaseRequest(purchaseRequest4, approverList);

    }

    private static void handlePurchaseRequest(PurchaseRequest purchaseRequest, List<Approver> approverList) {

        for (Approver approver: approverList) {

            if (approver.couldHandle(purchaseRequest)) {
                approver.handlePurchase(purchaseRequest);
                break;
            }
        }
    }
}
