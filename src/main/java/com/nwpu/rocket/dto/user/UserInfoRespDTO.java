package com.nwpu.rocket.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author zcy10
 * 回传给前端
 */
@Data
@Accessors(chain = true)
public class UserInfoRespDTO {

    private String tokenHead;

    private String token;

    private Long id;

    private String account;

    private String roles;

    private String name;

}
