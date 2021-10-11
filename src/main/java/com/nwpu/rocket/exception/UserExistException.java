package com.nwpu.rocket.exception;

import com.nwpu.rocket.until.exception.ApiException;

/**
 * @author zcy10
 */
public class UserExistException extends ApiException {
    public UserExistException() {
        super("用户已经存在");
        this.code = "A0111";
    }
}
