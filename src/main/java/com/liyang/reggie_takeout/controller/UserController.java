package com.liyang.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liyang.reggie_takeout.common.R;
import com.liyang.reggie_takeout.entity.User;
import com.liyang.reggie_takeout.service.UserService;
import com.liyang.reggie_takeout.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("code = {}", code);

//            session.setAttribute(phone, code);  不用session存储，改用Redis
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

            return R.success("发送成功！");
        }
        return R.error("发送失败！");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("map : {}", map.toString());

        String phone = map.get("phone").toString();

        String code = map.get("code").toString();


//        Object codeInSession = session.getAttribute(phone);       从redis获取验证码，而非session
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        if (code != null && code.equals(codeInSession)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            // 登陆成功，则从redis中删除验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }


        return R.error("验证登陆失败！");
    }
}
