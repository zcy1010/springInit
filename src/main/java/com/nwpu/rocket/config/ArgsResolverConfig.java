package com.nwpu.rocket.config;



import com.nwpu.rocket.config.security.userhandle.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zcy10
 */
@Configuration
public class ArgsResolverConfig implements WebMvcConfigurer {
    final CurrentUserMethodArgumentResolver resolver;


    @Autowired
    public ArgsResolverConfig(CurrentUserMethodArgumentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }
}