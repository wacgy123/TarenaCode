package com.cy.pj.common.pojo;

public class ResponseResult {
    private static final int SUCCESS=1;
    private static final int ERROR=0;
    private Integer state = SUCCESS;//1 success ,0 exception
    private String message = "ok";
    private Object data;

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseResult(String message) {
        this.message = message;
    }

    public ResponseResult(Object data) {
        this.data = data;
    }

    public ResponseResult(Throwable e) {
        this.state = 0;
        this.message = e.getMessage();
    }

}