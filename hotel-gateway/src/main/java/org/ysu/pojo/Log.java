package org.ysu.pojo;

public class Log {
    private Integer id;

    private Integer userId;

    private String operation;

    private String time;

    private String note;

    public Log(Integer id, Integer userId, String operation, String time, String note) {
        this.id = id;
        this.userId = userId;
        this.operation = operation;
        this.time = time;
        this.note = note;
    }

    public Log() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}