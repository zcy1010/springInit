package com.nwpu.rocket.service;



import com.nwpu.rocket.entity.SysLog;

import java.util.List;

/**
 * @author zcy
 * @Date 2021/4/17 22:06
 * @Version 1.0
 */
public interface SysLogService {
    /**
     * 添加新的log
     * @param sysLog
     * @return
     */
    SysLog addMyLog(SysLog sysLog);

    /**
     * 查找所有的系统日志
     * @return
     */
    List<SysLog> allSysLog();
//
//    List<User> getAllOnlineUser();

}
