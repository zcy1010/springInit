package com.nwpu.rocket.config.security;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 当用户没有访问权限时的处理器，用于返回JSON格式的处理结果
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Map<String, String> resp = new HashMap<>(6);
        resp.put("err_code", "A0312");
        resp.put("err_msg", "forbidden");
        resp.put("succeed", "false");
        JSON json = JSONUtil.parse(resp);
        response.getWriter().println(json);
        response.getWriter().flush();
    }
}
