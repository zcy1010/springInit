package com.nwpu.rocket.service;


import com.nwpu.rocket.entity.User;

/**
 * @author zcy10
 */
public interface AuthService {
    /**
     * 注册
     * @param userToAdd
     * @return
     */
    User register(User userToAdd);

    /**
     * 根据username和password返回token的字符串
     * @param username username
     * @param password password
     * @return token字符串
     */
    String login(String username, String password);

    /**
     * 暂时未用
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
}
