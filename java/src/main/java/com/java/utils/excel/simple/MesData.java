package com.java.utils.excel.simple;

import java.util.Date;

public class MesData {

    /**
     * 货主
     */
    private String storerkey;

    /**
     * 物料代码
     */
    private String sku;

    /**
     * 物料描述
     */
    private String descr;

    /**
     * 批次lot
     */
    private String lot;

    /**
     * 货位
     */
    private String loc;

    /**
     * lpn
     */
    private String id;

    /**
     * 现有量
     */
    private String qty;

    /**
     * 已分配库存
     */
    private Double qtyallocated;

    /**
     * 已检
     */
    private Double qtypicked;

    /**
     *
     */
    private Double qtyexpected;

    /**
     * 正在拣货
     */
    private Double qtypickinprocess;

    /**
     * 入库日期
     */
    private String lottable01;

    /**
     * 工厂
     */
    private String lottable02;

    /**
     * 库存地
     */
    private String lottable03;

    /**
     * 生产日期
     */
    private Date lottable04;

    /**
     * 到期日期
     */
    private Date lottable05;

    /**
     * 生产批次
     */
    private String lottable06;

    /**
     * 供应商代码
     */
    private String lottable07;

    /**
     * 供应商名称
     */
    private String company;

    /**
     * R3批次
     */
    private String lottable08;

    /**
     * 寄售自有
     */
    private String lottable09;

    /**
     * 质量状态：00是待检，10是合格
     */
    private String lottable10;

    /**
     *
     */
    private String pendingmovein;

    /**
     * 可用量
     */
    private Double available;

    /**
     * 冻结状态
     */
    private String status;

    public String getStorerkey() {
        return storerkey;
    }

    public void setStorerkey(String storerkey) {
        this.storerkey = storerkey;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getQtyallocated() {
        return qtyallocated;
    }

    public void setQtyallocated(Double qtyallocated) {
        this.qtyallocated = qtyallocated;
    }

    public Double getQtypicked() {
        return qtypicked;
    }

    public void setQtypicked(Double qtypicked) {
        this.qtypicked = qtypicked;
    }

    public Double getQtyexpected() {
        return qtyexpected;
    }

    public void setQtyexpected(Double qtyexpected) {
        this.qtyexpected = qtyexpected;
    }

    public Double getQtypickinprocess() {
        return qtypickinprocess;
    }

    public void setQtypickinprocess(Double qtypickinprocess) {
        this.qtypickinprocess = qtypickinprocess;
    }

    public String getLottable01() {
        return lottable01;
    }

    public void setLottable01(String lottable01) {
        this.lottable01 = lottable01;
    }

    public String getLottable02() {
        return lottable02;
    }

    public void setLottable02(String lottable02) {
        this.lottable02 = lottable02;
    }

    public String getLottable03() {
        return lottable03;
    }

    public void setLottable03(String lottable03) {
        this.lottable03 = lottable03;
    }

    public Date getLottable04() {
        return lottable04;
    }

    public void setLottable04(Date lottable04) {
        this.lottable04 = lottable04;
    }

    public Date getLottable05() {
        return lottable05;
    }

    public void setLottable05(Date lottable05) {
        this.lottable05 = lottable05;
    }

    public String getLottable06() {
        return lottable06;
    }

    public void setLottable06(String lottable06) {
        this.lottable06 = lottable06;
    }

    public String getLottable07() {
        return lottable07;
    }

    public void setLottable07(String lottable07) {
        this.lottable07 = lottable07;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLottable08() {
        return lottable08;
    }

    public void setLottable08(String lottable08) {
        this.lottable08 = lottable08;
    }

    public String getLottable09() {
        return lottable09;
    }

    public void setLottable09(String lottable09) {
        this.lottable09 = lottable09;
    }

    public String getLottable10() {
        return lottable10;
    }

    public void setLottable10(String lottable10) {
        this.lottable10 = lottable10;
    }

    public String getPendingmovein() {
        return pendingmovein;
    }

    public void setPendingmovein(String pendingmovein) {
        this.pendingmovein = pendingmovein;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
