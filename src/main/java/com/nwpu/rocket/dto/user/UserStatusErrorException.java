package com.nwpu.rocket.dto.user;

import com.nwpu.rocket.until.exception.ApiException;

/**
 * @author zcy
 * @Date 2021/5/1 16:50
 * @Version 1.0
 */
public class UserStatusErrorException extends ApiException {
    public UserStatusErrorException() {
        super("您已被锁定，请联系管理员");
        this.code = "A0604";
    }
}
