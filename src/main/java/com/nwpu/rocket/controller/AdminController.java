package com.nwpu.rocket.controller;

import com.nwpu.rocket.config.security.userhandle.CurrentUser;
import com.nwpu.rocket.dto.admin.AddAdminDTO;
import com.nwpu.rocket.dto.admin.EditUserStateDTO;
import com.nwpu.rocket.dto.user.UserInfoRespDTO;
import com.nwpu.rocket.dto.user.UserResetByAdminRequestDTO;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.exception.UserNotFoundException;
import com.nwpu.rocket.service.UserInfoService;
import com.nwpu.rocket.until.log.MyLog;
import com.nwpu.rocket.until.resp.Resp;
import com.nwpu.rocket.until.resp.RespFailed;
import com.nwpu.rocket.until.resp.RespSucceed;
import com.nwpu.rocket.until.tool.BeanConvertUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzp
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserInfoService userInfoService;

    public AdminController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
    /**
     * 获取所有超级管理员
     * test
     *
     * @return
     */
    @MyLog(value = "获取所有的admin")
    @GetMapping("/allAdmins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp getAllAdmin() {
        return new RespSucceed(userInfoService.findSpecificUsers(User.ROLE_ADMIN));
    }

    /**
     * 获取所有普通用户
     *
     * @return
     */
    @MyLog(value = "获取所有用户")
    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp getAllExpert() {
        return new RespSucceed(userInfoService.findSpecificUsers(User.ROLE_NORMAL));
    }

    /**
     * 添加超级管理员
     * test
     *
     * @param addAdminDTO
     * @return
     */
    @MyLog(value = "添加管理员")
    @PostMapping("/addAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp addAdmin(@RequestBody @Validated AddAdminDTO addAdminDTO) {
        User user = new User();
        user.setAccount(addAdminDTO.getAccount());
        user.setPassword(addAdminDTO.getPassword());
        User newUser = userInfoService.addAdmin(user);
        return new RespSucceed(newUser);
    }

    /**
     * 添加普通用户
     * test
     *
     * @param addAdminDTO
     * @return
     */
    @MyLog(value = "添加用户")
    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp addUser(@RequestBody @Validated AddAdminDTO addAdminDTO) {
        User user = new User();
        user.setAccount(addAdminDTO.getAccount());
        user.setPassword(addAdminDTO.getPassword());
        User newUser = userInfoService.addUser(user);
        return new RespSucceed(newUser);
    }

    /**
     * 管理员重置指定账户密码
     * @param userParam
     * @return
     */
    @MyLog(value = "重置密码")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/passwordReset")
    public Resp passwordReset(@RequestBody @Validated UserResetByAdminRequestDTO userParam) {
        try {
            User newUser = userInfoService.resetPassword(userParam.getAccount(), userParam.getNewPassword());
            UserInfoRespDTO response = BeanConvertUtils.convertTo(newUser, UserInfoRespDTO::new);
            return new RespSucceed(response);
        } catch (Exception e) {
            return new RespFailed("A0001", e.getMessage());
        }
    }

    /**
     * 锁定用户的帐号
     * test
     *
     * @param userParam
     * @return
     */
    @MyLog(value = "锁定用户的帐号")
    @PostMapping("/lockUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp lockUser(@RequestBody @Validated EditUserStateDTO userParam) {
        User user = userInfoService.findByAccount(userParam.getAccount());
        if (user == null) {
            throw new UserNotFoundException();
        }
        userInfoService.userStatusOnOffByUser(user, User.STATUS_OFF);
        return new RespSucceed(user);
    }

    /**
     * 解锁用户的账号
     * test
     *
     * @param userParam
     * @return
     */
    @MyLog(value = "解锁用户的账号")
    @PostMapping("/unLockUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Resp unLockUser(@RequestBody @Validated EditUserStateDTO userParam) {
        User user = userInfoService.findByAccount(userParam.getAccount());
        if (user == null) {
            throw new UserNotFoundException();
        }
        userInfoService.userStatusOnOffByUser(user, User.STATUS_ON);
        return new RespSucceed(user);
    }

    /**
     * 根据id查找
     * test
     *
     * @param user
     * @return
     */
    @MyLog(value = "查看个人信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getOne")
    public Resp findUserInfo(@CurrentUser User user) {
        User findUser = userInfoService.findById(user.getId());
        return new RespSucceed(findUser);
    }



}
