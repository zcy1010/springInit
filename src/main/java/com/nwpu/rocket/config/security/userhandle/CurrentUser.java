package com.nwpu.rocket.config.security.userhandle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解是指注解的注解。
 * @Target({ElementType.PARAMETER})// 方法参数(该注解用到方法中的参数） 元注解是指注解的注解。
 * @Retention(RetentionPolicy.RUNTIME)   // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * @author zcy10
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
