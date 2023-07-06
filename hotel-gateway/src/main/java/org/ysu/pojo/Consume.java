package org.ysu.pojo;

import java.math.BigDecimal;

public class Consume {
    private Integer id;

    private String no;

    private String roomNumber;

    private String goodsName;

    private Integer number;

    private BigDecimal goodsPrice;

    private BigDecimal consumeMoney;

    private BigDecimal discountRate;

    private BigDecimal discountMoney;

    private BigDecimal sumMoney;

    private Integer userId;

    private String date;

    private String note;

    public Consume(Integer id, String no, String roomNumber, String goodsName, Integer number, BigDecimal goodsPrice, BigDecimal consumeMoney, BigDecimal discountRate, BigDecimal discountMoney, BigDecimal sumMoney, Integer userId, String date, String note) {
        this.id = id;
        this.no = no;
        this.roomNumber = roomNumber;
        this.goodsName = goodsName;
        this.number = number;
        this.goodsPrice = goodsPrice;
        this.consumeMoney = consumeMoney;
        this.discountRate = discountRate;
        this.discountMoney = discountMoney;
        this.sumMoney = sumMoney;
        this.userId = userId;
        this.date = date;
        this.note = note;
    }

    public Consume() {
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(BigDecimal consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}