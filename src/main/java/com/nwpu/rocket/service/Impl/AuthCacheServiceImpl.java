package com.nwpu.rocket.service.Impl;





import com.nwpu.rocket.dto.user.UserAuthInRedis;
import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.service.AuthCacheService;
import com.nwpu.rocket.until.redis.RedisService;
import com.nwpu.rocket.until.tool.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zoe
 */
@Service
public class AuthCacheServiceImpl implements AuthCacheService {

    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.key.userauth}")
    private String REDIS_KEY_USER_AUTH;

    @Override
    public User getUser(String account) {
        return BeanConvertUtils.convertTo(redisService.get(getKey(account)), User::new);
    }

    @Override
    public void setUser(User user) {
        redisService.set(getKey(user.getAccount()),
                BeanConvertUtils.convertTo(user, UserAuthInRedis::new), REDIS_EXPIRE);
    }

    @Override
    public void delUser(String account) {
        redisService.del(getKey(account));
    }

    private String getKey(String username) {
        return REDIS_DATABASE + ":" + REDIS_KEY_USER_AUTH + ":" + username;
    }
}
