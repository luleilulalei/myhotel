package org.ysu.pojo;

import java.math.BigDecimal;

public class Checkout {
    private Integer id;

    private String no;

    private BigDecimal discountPrice;

    private BigDecimal deposit;

    private Integer days;

    private BigDecimal hotelCost;

    private BigDecimal shopCost;

    private BigDecimal mealCost;

    private BigDecimal phoneCost;

    private BigDecimal realCost;

    private BigDecimal reDeposit;

    private Integer paymentType;

    private BigDecimal receivedMoney;

    private BigDecimal changex;

    private Integer userId;

    private String note;

    public Checkout(Integer id, String no, BigDecimal discountPrice, BigDecimal deposit, Integer days, BigDecimal hotelCost, BigDecimal shopCost, BigDecimal mealCost, BigDecimal phoneCost, BigDecimal realCost, BigDecimal reDeposit, Integer paymentType, BigDecimal receivedMoney, BigDecimal changex, Integer userId, String note) {
        this.id = id;
        this.no = no;
        this.discountPrice = discountPrice;
        this.deposit = deposit;
        this.days = days;
        this.hotelCost = hotelCost;
        this.shopCost = shopCost;
        this.mealCost = mealCost;
        this.phoneCost = phoneCost;
        this.realCost = realCost;
        this.reDeposit = reDeposit;
        this.paymentType = paymentType;
        this.receivedMoney = receivedMoney;
        this.changex = changex;
        this.userId = userId;
        this.note = note;
    }

    public Checkout() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(BigDecimal hotelCost) {
        this.hotelCost = hotelCost;
    }

    public BigDecimal getShopCost() {
        return shopCost;
    }

    public void setShopCost(BigDecimal shopCost) {
        this.shopCost = shopCost;
    }

    public BigDecimal getMealCost() {
        return mealCost;
    }

    public void setMealCost(BigDecimal mealCost) {
        this.mealCost = mealCost;
    }

    public BigDecimal getPhoneCost() {
        return phoneCost;
    }

    public void setPhoneCost(BigDecimal phoneCost) {
        this.phoneCost = phoneCost;
    }

    public BigDecimal getRealCost() {
        return realCost;
    }

    public void setRealCost(BigDecimal realCost) {
        this.realCost = realCost;
    }

    public BigDecimal getReDeposit() {
        return reDeposit;
    }

    public void setReDeposit(BigDecimal reDeposit) {
        this.reDeposit = reDeposit;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(BigDecimal receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public BigDecimal getChangex() {
        return changex;
    }

    public void setChangex(BigDecimal changex) {
        this.changex = changex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}