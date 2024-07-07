package com.liyang.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liyang.reggie_takeout.common.R;
import com.liyang.reggie_takeout.entity.User;
import com.liyang.reggie_takeout.service.UserService;
import com.liyang.reggie_takeout.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("code = {}", code);

            session.setAttribute(phone, code);
            return R.success("发送成功！");
        }
        return R.error("发送失败！");
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody Map map, HttpSession session) {
        log.info("map : {}", map.toString());

        String 


        return null;
    }
}
