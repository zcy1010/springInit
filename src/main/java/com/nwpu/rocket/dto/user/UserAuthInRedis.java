package com.nwpu.rocket.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author zcy10
 */
@Data
public class UserAuthInRedis {
    private Long id;
    private String account;
    @Length(min=6,message="密码长度不能小于6位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码只能由数字和字母组成且必须同时存在")
    private String password;
    private String roles;
    private Integer status;
    private Integer enabled;
}
