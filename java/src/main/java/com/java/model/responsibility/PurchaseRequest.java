package com.java.model.responsibility;

/**
 * @author Ning
 * @date Create in 2019/4/20
 */
public class PurchaseRequest {

    private double price;
    private int number;
    private String purpose;

    public PurchaseRequest(double price, int number, String purpose) {
        this.price = price;
        this.number = number;
        this.purpose = purpose;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "采购单: " + getPurpose() + ", 金额: " + getPrice() + ", 数量" +  getNumber();
    }
}
