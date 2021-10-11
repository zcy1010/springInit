package com.nwpu.rocket.exception;

import com.nwpu.rocket.until.exception.ApiException;

/**
 * @author zcy
 * @Date 2021/5/3 15:02
 * @Version 1.0
 */
public class InsufficientPermissionException extends ApiException {
    public InsufficientPermissionException() {
        super("权限不足");
        this.code="A0122";
    }
}
