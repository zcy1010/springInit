package com.nwpu.rocket.service.Impl;




import com.nwpu.rocket.entity.SysLog;
import com.nwpu.rocket.repository.SysLogRepository;
import com.nwpu.rocket.service.SysLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcy
 * @Date 2021/4/17 22:09
 * @Version 1.0
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    private final SysLogRepository sysLogRepository;

    public SysLogServiceImpl(SysLogRepository sysLogRepository) {
        this.sysLogRepository = sysLogRepository;
    }


    @Override
    public SysLog addMyLog(SysLog sysLog) {

        return sysLogRepository.saveAndFlush(sysLog);
    }

    @Override
    public List<SysLog> allSysLog() {
        return sysLogRepository.findAll();

    }
//
//    @Override
//    public List<User> getAllOnlineUser() {
//        List<SysLog> sysLogList = sysLogRepository.findAll();
//        List<User> userList=new ArrayList<>();
//        Date date=new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        for(SysLog sysLog:sysLogList){
//            if(sysLog.getOperation().equals("登录") ){
//                Long nowTime=date.getTime();
//                Long loginTime=sysLog.getRegisterTime().getTime();
//                //相差分钟数
//                int timeDifference = (int)((nowTime - loginTime) / (1000*60));
//                if(timeDifference<=180){
//                    User user=sysLog.getUser();
//                    if(!userList.contains(user)){
//                        userList.add(user);
//                    }
//                }
//            }
//        }
//
//        return userList;
//    }
}
