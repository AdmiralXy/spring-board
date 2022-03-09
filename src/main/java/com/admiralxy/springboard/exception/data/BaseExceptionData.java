package com.admiralxy.springboard.exception.data;

public class BaseExceptionData {
    private int code;
    private String message;

    public BaseExceptionData() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
