package com.nwpu.rocket.config.security;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 * Created by macro on 2018/5/14.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(200);
        Map<String, String> resp = new HashMap<>(6);
        resp.put("err_code", "A0220");
        resp.put("err_msg", "authentication failed");
        resp.put("succeed", "false");
        JSON json = JSONUtil.parse(resp);
        response.getWriter().println(json);
        response.getWriter().flush();
    }
}
