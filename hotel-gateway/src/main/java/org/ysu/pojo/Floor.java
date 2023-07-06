package org.ysu.pojo;

public class Floor {
    private Integer id;

    private String name;

    private String note;

    public Floor(Integer id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public Floor() {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}