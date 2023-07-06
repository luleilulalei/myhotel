package org.ysu.pojo;

import java.math.BigDecimal;

public class Goods {
    private Integer id;

    private String name;

    private Integer goodsTypeId;

    private BigDecimal price;

    private String unit;

    private String note;

    public Goods(Integer id, String name, Integer goodsTypeId, BigDecimal price, String unit, String note) {
        this.id = id;
        this.name = name;
        this.goodsTypeId = goodsTypeId;
        this.price = price;
        this.unit = unit;
        this.note = note;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}