package com.nwpu.rocket.service.Impl;



import com.nwpu.rocket.config.security.userhandle.JwtUserDetails;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.repository.UserRepository;
import com.nwpu.rocket.service.AuthCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * spring security 验证用户实现
 * @author zcy10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private final AuthCacheService cacheService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, AuthCacheService cacheService) {
        this.userRepository = userRepository;
        this.cacheService = cacheService;
    }

    /**
     * 先根据用户的account从redis缓存中查找是否存这该内容，是：返回JwtUserDetails格式的User）包括User和权限列表
     *                                              否：在redis缓存中新建相关user的存储 ，再返回JwtUserDetails格式的User）包括User和权限列表
     * @param account
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = cacheService.getUser(account);
        if (user != null) {
            return new JwtUserDetails(user);
        }
        user = userRepository.findByAccount(account);
        if (user != null) {
            cacheService.setUser(user);
            return new JwtUserDetails(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
