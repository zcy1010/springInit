package com.nwpu.rocket.controller;


import com.nwpu.rocket.entity.SysLog;
import com.nwpu.rocket.service.SysLogService;
import com.nwpu.rocket.service.UserInfoService;
import com.nwpu.rocket.until.log.MyLog;
import com.nwpu.rocket.until.resp.Resp;
import com.nwpu.rocket.until.resp.RespSucceed;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcy
 * @Date 2021/4/20 17:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/sysLog")
public class SysLogController {
    private final SysLogService sysLogService;
    private final UserInfoService userInfoService;

    public SysLogController(SysLogService sysLogService, UserInfoService userInfoService) {
        this.sysLogService = sysLogService;
        this.userInfoService = userInfoService;
    }

    @MyLog(value = "查看所有日志")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allSysLog")
    public Resp getAllSysLog(){
        List<SysLog> allSysLog=sysLogService.allSysLog();
        return new RespSucceed(allSysLog);
    }



}
