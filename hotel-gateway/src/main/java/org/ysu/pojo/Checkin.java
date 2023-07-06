package org.ysu.pojo;

import java.math.BigDecimal;

public class Checkin {
    private Integer id;

    private String no;

    private String roomNumber;

    private Integer roomTypeId;

    private BigDecimal standardPrice;

    private BigDecimal discountPrice;

    private BigDecimal deposit;

    private String customerName;

    private Integer cardType;

    private String cardNumber;

    private String phone;

    private String arriveTime;

    private String leaveTime;

    private Integer customerSize;

    private Integer userId;

    private Integer customerId;

    private BigDecimal customerPrice;

    private Integer breakfastOrNot;

    private Integer remindOrNot;

    private String note;

    public Checkin(Integer id, String no, String roomNumber, Integer roomTypeId, BigDecimal standardPrice, BigDecimal discountPrice, BigDecimal deposit, String customerName, Integer cardType, String cardNumber, String phone, String arriveTime, String leaveTime, Integer customerSize, Integer userId, Integer customerId, BigDecimal customerPrice, Integer breakfastOrNot, Integer remindOrNot, String note) {
        this.id = id;
        this.no = no;
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
        this.standardPrice = standardPrice;
        this.discountPrice = discountPrice;
        this.deposit = deposit;
        this.customerName = customerName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.phone = phone;
        this.arriveTime = arriveTime;
        this.leaveTime = leaveTime;
        this.customerSize = customerSize;
        this.userId = userId;
        this.customerId = customerId;
        this.customerPrice = customerPrice;
        this.breakfastOrNot = breakfastOrNot;
        this.remindOrNot = remindOrNot;
        this.note = note;
    }

    public Checkin() {
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

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime == null ? null : arriveTime.trim();
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime == null ? null : leaveTime.trim();
    }

    public Integer getCustomerSize() {
        return customerSize;
    }

    public void setCustomerSize(Integer customerSize) {
        this.customerSize = customerSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(BigDecimal customerPrice) {
        this.customerPrice = customerPrice;
    }

    public Integer getBreakfastOrNot() {
        return breakfastOrNot;
    }

    public void setBreakfastOrNot(Integer breakfastOrNot) {
        this.breakfastOrNot = breakfastOrNot;
    }

    public Integer getRemindOrNot() {
        return remindOrNot;
    }

    public void setRemindOrNot(Integer remindOrNot) {
        this.remindOrNot = remindOrNot;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}