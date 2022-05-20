package com.example.sumapplication.model;

public class NotFoundException {
    private String error;
    private String errorCode;
    private String message;
    private String detail;

    public NotFoundException(String error, String errorCode, String message, String detail) {
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
