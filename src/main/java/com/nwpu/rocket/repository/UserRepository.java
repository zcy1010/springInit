package com.nwpu.rocket.repository;


import com.nwpu.rocket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zcy10
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过account 查找用户
     *
     * @param account 账号
     * @return user对象
     */
    User findByAccount(String account);

    /**
     * 通过id获取user
     *
     * @param id
     * @return
     */
    User findById(long id);

    /**
     * 通过role查找所有的对应对象
     *
     * @param roles 身份
     * @return 相关对象
     */
    List<User> findAllByRoles(String roles);



}
