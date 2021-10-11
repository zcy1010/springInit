package com.nwpu.rocket.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDTO {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$", message = "6-12个字符，至少1个字母和1个数字,不能包含特殊字符（非数字字母)")
    private String password;
}
