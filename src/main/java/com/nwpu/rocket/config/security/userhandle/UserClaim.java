package com.nwpu.rocket.config.security.userhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用来存储token中的user信息的vo
 *
 * @author zy
 */
@Getter
@AllArgsConstructor
public class UserClaim {
    private final long id;
    private final String account;
    private final String role;
}
