package ru.novikova.hibernate_springboot.exceptions;

import java.util.Date;

public class CommonError {
    private String message;
    private Date date;

    public CommonError() {
        this.date = new Date();
    }

    public CommonError(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
