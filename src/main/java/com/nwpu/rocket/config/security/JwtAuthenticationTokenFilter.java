package com.nwpu.rocket.config.security;


//import com.edu.nwpu.util.JwtTokenUtil;
//import com.edu.nwpu.util.security.userhandle.JwtUserDetails;
//import com.edu.nwpu.util.security.userhandle.UserClaim;
import com.nwpu.education.edu_backend.util.JwtTokenUtil;
import com.nwpu.education.edu_backend.util.security.userhandle.JwtUserDetails;
import com.nwpu.education.edu_backend.util.security.userhandle.UserClaim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
 * 用于每次外部对接口请求时的Token处理
 *
 * @author zy
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException, NullPointerException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());
            // The part after "Bearer "
            UserClaim user = jwtTokenUtil.getUserDataFromToken(authToken);
            String username = user.getAccount();
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                JwtUserDetails userDetails = (JwtUserDetails) this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    /*
                    将已有的用户信息添加到request里
                     */
                    request.setAttribute("uid", user.getId());
                    request.setAttribute("sub", username);
                    request.setAttribute("role", user.getRole());
                }
            }
        }
        chain.doFilter(request, response);
    }
}
