package com.lms.exception;

public class InvalidCouponException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidCouponException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
