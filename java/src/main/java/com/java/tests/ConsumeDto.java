package com.java.tests;


import java.lang.reflect.Field;

public class ConsumeDto {

    private String tradeId;
    private String accountNum;
    private String unit;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static void main(String[] args) throws Exception {
        ConsumeDto consumeDto = new ConsumeDto();
        consumeDto.setAccountNum("ssss");

        Class clazz =  consumeDto.getClass();
        Field field = consumeDto.getClass().getDeclaredField("accountNum");
        String s = (String) field.get(consumeDto);
        System.out.println("");
    }
}
