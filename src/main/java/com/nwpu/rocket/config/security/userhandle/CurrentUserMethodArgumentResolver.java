package com.nwpu.rocket.config.security.userhandle;



import com.nwpu.education.edu_backend.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *  增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @author zcy10
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String account = (String) webRequest.getAttribute("sub", RequestAttributes.SCOPE_REQUEST);
        Long id = (Long) webRequest.getAttribute("uid", RequestAttributes.SCOPE_REQUEST);
        String role = (String) webRequest.getAttribute("role", RequestAttributes.SCOPE_REQUEST);
        return new User().setId(id).setAccount(account).setRoles(role);
    }
}