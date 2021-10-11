package com.nwpu.rocket.exception;

import com.nwpu.rocket.until.exception.ApiException;

/**
 * @author zcy10
 */
public class PasswordWrongException extends ApiException {
    public PasswordWrongException() {
        super("密码错误");
        this.code = "A0200";
    }
}
