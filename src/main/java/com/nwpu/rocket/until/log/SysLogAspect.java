package com.nwpu.rocket.until.log;



//import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.nwpu.education.edu_backend.entity.SysLog;
import com.nwpu.education.edu_backend.entity.User;
import com.nwpu.education.edu_backend.service.SysLogService;
import com.nwpu.education.edu_backend.service.UserInfoService;
import com.nwpu.education.edu_backend.util.security.userhandle.JwtUserDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * @author zcy
 * @Date 2021/4/17 22:05
 * @Version 1.0
 */
//作用是把当前类标识为一个切面供容器读取
@Aspect
//把普通 pojo 实例化到 spring 容器中
@Component
public class SysLogAspect {

    private final SysLogService sysLogService;
    private final UserInfoService userInfoService;

    public SysLogAspect(SysLogService sysLogService, UserInfoService userInfoService) {
        this.sysLogService = sysLogService;
        this.userInfoService = userInfoService;
    }

    @Pointcut("@annotation( com.nwpu.education.edu_backend.util.log.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    //JointPoint是程序运行过程中可识别的点，这个点可以用来作为AOP切入点。JointPoint对象则包含了和切入相关的很多信息。比如切入点的对象，方法，属性等。我们可以通过反射的方式获取这些点的状态和信息，用于追踪tracing和记录logging应用信息。
    //Proceedingjoinpoint 继承了 JoinPoint。是在JoinPoint的基础上暴露出 proceed 这个方法。proceed很重要，这个是aop代理链执行的方法。
    @Around("logPoinCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveSysLog(point, time);
        return result;
    }


    public void saveSysLog(ProceedingJoinPoint joinPoint, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog=new SysLog();
        com.nwpu.education.edu_backend.util.log.MyLog myLog=method.getAnnotation(com.nwpu.education.edu_backend.util.log.MyLog.class);
        if(myLog!=null){
            sysLog.setOperation(myLog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //将参数所在的数组转换成json
        String params = new Gson().toJson(joinPoint.getArgs());

//        try {
//            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
//            sysLog.setParams(params);
//        } catch (Exception e) {
//
//        }
        sysLog.setParams(params);
        // 获取request
        HttpServletRequest request = com.nwpu.education.edu_backend.util.log.HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        SecurityContext context= SecurityContextHolder.getContext();
        JwtUserDetails user=(JwtUserDetails)context.getAuthentication().getPrincipal();
        User currentUser=user.getUser();
        sysLog.setUser(currentUser);
        sysLog.setTime(time);
        sysLogService.addMyLog(sysLog);
    }
}