package com.nwpu.rocket.dto.user;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用于重置用户的密码以及角色
 * @author zzp
 */
@Data
public class UserResetByAdminRequestDTO {
    @NotBlank(message = "账号不能为空")
    private String account;

    @Length(min=6,message="密码长度不能小于6位")
    private String newPassword;

    private String roles;
}
