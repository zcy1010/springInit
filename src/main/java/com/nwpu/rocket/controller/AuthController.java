package com.nwpu.rocket.controller;


import com.nwpu.rocket.config.security.userhandle.CurrentUser;
import com.nwpu.rocket.dto.user.UserAuthRequestDTO;
import com.nwpu.rocket.dto.user.UserInfoRespDTO;
import com.nwpu.rocket.dto.user.UserLoginDTO;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.service.AuthService;
import com.nwpu.rocket.service.UserInfoService;
import com.nwpu.rocket.until.log.MyLog;
import com.nwpu.rocket.until.resp.Resp;
import com.nwpu.rocket.until.resp.RespFailed;
import com.nwpu.rocket.until.resp.RespSucceed;
import com.nwpu.rocket.until.tool.BeanConvertUtils;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zcy10
 */
@RestController
@Api(tags = "用户验证")
@RequestMapping("/api/auth")


public class AuthController {
    private final AuthService authService;
    private final UserInfoService userInfoService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 用户注册
     *
     * @param userParam userParam
     * @return 返回是否成功的相关信息
     */
    private static final String ROLE_PREFIX = "ROLE_";

    public AuthController(AuthService authService, UserInfoService userInfoService) {
        this.authService = authService;
        this.userInfoService = userInfoService;
    }

    /**
     * 注册
     *
     * @param userParam
     * @return
     */
    @PostMapping("/register")
    public Resp register(@RequestBody @Validated UserAuthRequestDTO userParam) {
        Assert.notNull(userParam.getRoles(), "用户权限为空");
        if (!userParam.getRoles().startsWith(ROLE_PREFIX)) {
            throw new IllegalArgumentException();
        }
        User user = BeanConvertUtils.convertTo(userParam, User::new);
        user = authService.register(user);
        if (user == null) {
            return new RespFailed("A0111", "用户名已存在");
        }
        user.setPassword(null);
        return new RespSucceed(BeanConvertUtils.convertTo(user, UserAuthRequestDTO::new));
    }
//
//    @Deprecated
//    @PostMapping("/token")
//    public Resp auth(@RequestBody UserAuthRequestDTO userRequest) {
//        try {
//            String token = authService.login(userRequest.getAccount(), userRequest.getPassword());
//            if (token == null) {
//                return new RespFailed("A0001", "login failed.");
//            }
//            Map<String, String> tokenMap = new HashMap<>(8);
//            tokenMap.put("token", token);
//            tokenMap.put("tokenHead", tokenHead);
//            tokenMap.put("account", userRequest.getAccount());
//            tokenMap.put("role", userRequest.getRoles());
//            return new RespSucceed(tokenMap);
//        } catch (AuthenticationException e) {
//            return new RespFailed("A0001", e.getMessage());
//        }
//    }

    @MyLog(value = "登录")
    @PostMapping("/login")
    public Resp login(@RequestBody @Validated UserLoginDTO userRequest) {
        try {
            String token = authService.login(userRequest.getAccount(), userRequest.getPassword());
            if (token == null) {
                return new RespFailed("A0001", "login failed.");
            }
            User user = userInfoService.findByAccount(userRequest.getAccount());
            UserInfoRespDTO response = BeanConvertUtils.convertTo(user, UserInfoRespDTO::new);
            return new RespSucceed(response.setToken(token).setTokenHead(tokenHead));
        } catch (AuthenticationException e) {
            return new RespFailed("A0001", e.getMessage());
        }
    }

    //    @PostMapping("/admin/add")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Resp addAdmin(@RequestBody AddAdminDTO userParam) {
//        if (!userParam.getPassword().equals(userParam.getPasswordRepeated())) {
//            throw new PasswordNotSame();
//        }
//        if (!userParam.getRoles().equals(User.ROLE_ADMIN)) {
//            throw new AddedUserPermissionsIncorrectException();
//        }
//        User user = BeanConvertUtils.convertTo(userParam, User::new);
//        userInfoService.addAdmin(user);
//        return new RespSucceed(user);
//    }
    @GetMapping("/my")
    public Resp getUser(@CurrentUser User user) {
        user = userInfoService.findByAccount(user.getAccount());
        return new RespSucceed(user.setPassword(""));
    }


}
