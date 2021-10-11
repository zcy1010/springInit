package com.nwpu.rocket.until.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    protected String code;

    protected ApiException(String message) {
        super(message);
    }

    public ApiException(String message, String code) {
        super(message);
        this.code = code;
    }
}