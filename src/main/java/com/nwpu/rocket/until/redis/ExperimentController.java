package com.nwpu.rocket.until.redis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用redis的一些实验性功能
 *
 * @author zy
 */

@RestController
@Deprecated
@RequestMapping("/api/experiment")
public class ExperimentController {
//    @Autowired
//    UserInfoService userInfoService;
//
//    @Autowired
//    RedisService redisService;
//
//    @Value("${redis.database}")
//    private String REDIS_DATABASE;
//
//    /**
//     * 10 min 过期
//     */
//    private int REDIS_EXPIRE = 10 * 60;
//
//    @Value("${redis.key.emailcode}")
//    private String REDIS_KEY_EMAIL_CODE;
//
//    /**
//     * 得到手机验证码
//     */
//    @GetMapping("/password")
//    public Resp changePasswordWithMail(@CurrentUser User user) {
//        user = userInfoService.findById(user.getId());
//        String email = user.getEmail();
//        if (email == null || !email.contains("@") || email.length() < 4) {
//            return new RespFailed("A0000", "wrong email");
//        }
//
//        String key = REDIS_DATABASE + ":" + REDIS_KEY_EMAIL_CODE + ":" + user.getAccount();
//
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 6; i++) {
//            sb.append(random.nextInt(10));
//        }
//
//        redisService.set(key, sb.toString(), REDIS_EXPIRE);
//
//        Map<String, Object> result = new HashMap<>(4);
//        result.put("code", sb.toString());
//        //send email
//        MailUtil.send(email, "重置你的密码", "你的验证码为：" + sb.toString(), false);
//
//        return new RespSucceed(result);
//    }
//
//    @PostMapping("/password")
//    public Resp verifyCode(@Validated UserPasswordEditDTO passwordDto,
//                           @CurrentUser User user) {
//
//        String key = REDIS_DATABASE + ":" + REDIS_KEY_EMAIL_CODE + ":" + user.getAccount();
//
//        String code = passwordDto.getCode();
//        if (code.length() != 6) {
//            return new RespFailed("", "wrong code");
//        }
//        String correctCode = (String) redisService.get(key);
//        if (correctCode.equals(code)) {
//            redisService.del(key);
//            return new RespSucceed(userInfoService.changePassword(user, passwordDto.getOldPassword(), passwordDto.getNewPassword()));
//        }
//        return new RespFailed("", "wrong code");
//    }
}
