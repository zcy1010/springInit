package com.nwpu.rocket.service;


import com.nwpu.rocket.entity.User;

/**
 * @author zcy10
 */
public interface AuthCacheService {
    /**
     * 将account转化为key
     * 在通过key去寻找redis中存储的内容
     * 并将存储的内容转换为User类型
     * @param account 用户的account
     * @return User
     */
    User getUser(String account);

    /**
     * 通过user找到user的account
     * 通过account建立存储到redis中的key，
     * 再将user等相关内容存储到redis中
     * @param user user
     */
    void setUser(User user);

    /**
     * 删除redis里面的信息
     * @param account account
     */
    void delUser(String account);
}
