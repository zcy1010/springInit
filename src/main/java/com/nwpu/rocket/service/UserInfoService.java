package com.nwpu.rocket.service;




import com.nwpu.rocket.entity.User;

import java.util.List;

/**
 * @author zcy10
 */
public interface UserInfoService {
    /**
     * 通过account查找User
     *
     * @param account
     * @return
     */
    User findByAccount(String account);

    /**
     * 添加admin
     *
     * @param newUser 要添加的User
     * @return newUser
     */
    User addAdmin(User newUser);

    /**
     * 添加用户
     *
     * @param newUser 要添加的User
     * @return newUser
     */
    User addUser(User newUser);

    /**
     * 设置用户的状态
     *
     * @param user
     * @return
     */
    User userStatusOnOffByUser(User user, Integer status);

    List<User> findSpecificUsers(String role);

    /**
     * 跟新user信息
     *
     * @param user
     * @return
     */
    User updateUser(User user, String name, String phone, String email);

    /**
     * @param user
     * @param oldPassword
     * @param newPassword
     * @return
     */
    User changePassword(User user, String oldPassword, String newPassword);

    /**
     * 通过id获取User
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 上级重置下级密码
     * @param account
     * @param newPassword
     * @param role 下级的身份
     * @return
     */
    User resetPassword(String account, String newPassword);
}
