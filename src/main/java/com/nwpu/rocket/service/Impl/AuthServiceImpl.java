package com.nwpu.rocket.service.Impl;


import com.nwpu.rocket.config.security.userhandle.JwtUserDetails;
import com.nwpu.rocket.dto.user.UserStatusErrorException;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.exception.PasswordWrongException;
import com.nwpu.rocket.exception.UserExistException;
import com.nwpu.rocket.repository.UserRepository;
import com.nwpu.rocket.service.AuthService;
import com.nwpu.rocket.service.UserInfoService;
import com.nwpu.rocket.until.token.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zy
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserInfoService userInfoService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserRepository userRepository, UserInfoService userInfoService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.userInfoService = userInfoService;
    }

    @Override
    public User register(User userToAdd) {
        final String account = userToAdd.getAccount();
        User user = userRepository.findByAccount(account);
        if (user != null) {
            throw new UserExistException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setPasswordClearText(rawPassword);
        return userRepository.saveAndFlush(userToAdd);
    }

    @Override
    public String login(String account, String password) throws AuthenticationException {
        User user= userInfoService.findByAccount(account);
        if(!password.equals(user.getPasswordClearText())){
            throw new PasswordWrongException();
        }
        if(user.getStatus()==User.STATUS_OFF){
            throw new UserStatusErrorException();
        }
        //希望创建UsernamePasswordAuthenticationToken的任何代码都可以安全地使用此构造方法，因为isAuthenticated（）将返回false。
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account, password);
        //尝试对传递的Authentication对象进行身份验证，如果成功，则返回完全填充的Authentication对象（包括授予的权限）。
        final Authentication authentication = authenticationManager.authenticate(upToken);
        //更改当前已认证的主体，或删除认证信息。
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //根据用户名找到用户。从redis里面查找。其余内容（密码状态等）可能没更新？
        final UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        //根据redis里面的user的信息生成token
        return jwtTokenUtil.generateToken((JwtUserDetails) userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUserAccountFromToken(token);
        JwtUserDetails user = (JwtUserDetails) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}