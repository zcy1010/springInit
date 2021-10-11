package com.nwpu.rocket.exception;

import com.nwpu.rocket.until.exception.ApiException;
import lombok.Getter;

@Getter
public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super("暂无该用户");
        this.code = "A0201";
    }
}
