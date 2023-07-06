package org.ysu.pojo;

import java.math.BigDecimal;

public class Room {
    private Integer id;
//    public static final Integer ROOT_STATE_USING = 1;
//    public static final Integer ROOT_STATE_FREE = 2;
//    public static final Integer ROOT_STATE_RESERVE = 3;

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    private String floorName;

    private String type;

    private String roomNumber;

    private Integer state; //三种取值  空房  已预定 入住

    private BigDecimal standardPrice;

    private BigDecimal discountPrice;

    private BigDecimal ncustomerPrice;

    private BigDecimal vcustomerPrice;

    private String note;

    public Room(Integer id, String floorName, String type, String roomNumber, Integer state, BigDecimal standardPrice, BigDecimal discountPrice, BigDecimal ncustomerPrice, BigDecimal vcustomerPrice, String note) {
        this.id = id;
        this.floorName = floorName;
        this.type = type;
        this.roomNumber = roomNumber;
        this.state = state;
        this.standardPrice = standardPrice;
        this.discountPrice = discountPrice;
        this.ncustomerPrice = ncustomerPrice;
        this.vcustomerPrice = vcustomerPrice;
        this.note = note;
    }

    public Room() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public BigDecimal getNcustomerPrice() {
        return ncustomerPrice;
    }

    public void setNcustomerPrice(BigDecimal ncustomerPrice) {
        this.ncustomerPrice = ncustomerPrice;
    }

    public BigDecimal getVcustomerPrice() {
        return vcustomerPrice;
    }

    public void setVcustomerPrice(BigDecimal vcustomerPrice) {
        this.vcustomerPrice = vcustomerPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}