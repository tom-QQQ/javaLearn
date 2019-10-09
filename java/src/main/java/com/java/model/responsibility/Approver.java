package com.java.model.responsibility;

/**
 * @author Ning
 * @date Create in 2019/4/20
 */
public interface Approver {

    /**
     * 处理采购单
     * @param purchaseRequest 采购申请
     */
    void handlePurchase(PurchaseRequest purchaseRequest);

    /**
     * 能否处理采购单
     * @param purchaseRequest 采购申请
     * @return 能否处理
     */
    boolean couldHandle(PurchaseRequest purchaseRequest);
}
