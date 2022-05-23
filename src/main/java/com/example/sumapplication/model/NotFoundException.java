package com.example.sumapplication.model;

public class NotFoundException {
    private final String error;
    private final String errorCode;
    private final String message;
    private final String detail;

    public NotFoundException(String error, String errorCode, String message, String detail) {
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public String getError() {
        return error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
