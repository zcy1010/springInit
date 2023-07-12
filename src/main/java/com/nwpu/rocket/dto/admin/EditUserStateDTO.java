package com.nwpu.rocket.dto.admin;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 更改用户状态的DTO
 *
 * @author zzp
 */
@Data
public class EditUserStateDTO {
    @NotBlank(message = "账号不能为空")
    private String account;
}
