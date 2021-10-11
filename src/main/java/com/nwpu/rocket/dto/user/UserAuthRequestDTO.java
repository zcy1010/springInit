package com.nwpu.rocket.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * @author zcy
 */
@Data
public class UserAuthRequestDTO {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
//    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码只能由数字和字母组成且必须同时存在")
    private String password;

    private String roles;
}