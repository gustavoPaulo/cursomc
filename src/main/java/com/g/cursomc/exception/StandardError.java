package com.g.cursomc.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

    private Integer status;
    private String message;
    private String dateTime;

    public StandardError(Integer status, String message, String dateTime) {
        this.status = status;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
