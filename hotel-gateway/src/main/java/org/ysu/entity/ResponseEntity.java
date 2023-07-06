package org.ysu.entity;

public class ResponseEntity {
    protected Integer status;
    protected String msg;
    protected Object data;

    public ResponseEntity(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntity(Object data) {
        this.status = HttpStatus.OK;
        this.msg = "";
        this.data = data;
    }

    public ResponseEntity(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
