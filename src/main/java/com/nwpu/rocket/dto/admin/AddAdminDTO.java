package com.nwpu.rocket.dto.admin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * test
 * 添加admin参数
 * Description: 添加admin参数
 * date: 2021/4/8 17:30
 * @author cyq
 * @since JDK 1.8
 */
@Data
public class AddAdminDTO {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码只能由数字和字母组成且必须同时存在")
    private String password;
}
