package com.nwpu.rocket.until.token;



import com.nwpu.rocket.config.security.userhandle.JwtUserDetails;
import com.nwpu.rocket.config.security.userhandle.UserClaim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    private static final String CLAIM_KEY_USERID = "uid";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLE = "role";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * private
     * 从数据声明生成令牌
     * 根据负载生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     * 从令牌中获取数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
            return null;
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserAccountFromToken(String token) {
        return getUserDataFromToken(token).getAccount();
    }

    /**
     * 从token中获取登录用户名
     */
    public UserClaim getUserDataFromToken(String token) {
        UserClaim userClaim;
        try {
            Claims claims = getClaimsFromToken(token);
            String account = claims.get("sub", String.class);
            String role = claims.get("role", String.class);
            long id = claims.get("uid", Long.class);
            userClaim = new UserClaim(id, account, role);
        } catch (Exception e) {
            userClaim = null;
        }
        return userClaim;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, JwtUserDetails userDetails) {
        String account = getUserAccountFromToken(token);
        return account.equals(userDetails.getUser().getAccount()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     * payload部分内容在此添加
     */
    public String generateToken(JwtUserDetails user) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(CLAIM_KEY_USER_ACCOUNT, user.getUser().getAccount());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_USERID, user.getUser().getId());
        claims.put(CLAIM_KEY_ROLE, user.getUser().getRoles());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
