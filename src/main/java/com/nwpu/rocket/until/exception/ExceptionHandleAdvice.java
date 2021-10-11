package com.nwpu.rocket.until.exception;

import com.nwpu.rocket.until.resp.Resp;
import com.nwpu.rocket.until.resp.RespFailed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zy
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {
    /**
     * 请求体参数格式错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    public Resp methodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new RespFailed("wrong request param", objectError.getDefaultMessage());
    }

    /**
     * 能够处理所有继承了 ApiException类 的错误
     *
     * @param e exception
     * @return fail resp
     */
    @ExceptionHandler({ApiException.class})
    public Resp apiExceptionHandler(ApiException e) {
        log.warn("api exception", e.getMessage());
        return new RespFailed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public Resp exc(IllegalArgumentException e) {
        return new RespFailed("A0100", e.getMessage());
    }
}
