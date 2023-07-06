package org.ysu.pojo;

public class Roomtype {
    private Integer id;

    private String name;

    private Integer size;

    private String note;

    public Roomtype(Integer id, String name, Integer size, String note) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.note = note;
    }

    public Roomtype() {
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}