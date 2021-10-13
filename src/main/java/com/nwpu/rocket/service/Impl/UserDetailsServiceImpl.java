package com.nwpu.rocket.service.Impl;



import com.nwpu.rocket.config.security.userhandle.JwtUserDetails;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.repository.UserRepository;
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


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 先返回JwtUserDetails格式的User）包括User和权限
     * @param account
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(account);
        return new JwtUserDetails(user);
    }
}
