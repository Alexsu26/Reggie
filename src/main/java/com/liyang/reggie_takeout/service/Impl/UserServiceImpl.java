package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.entity.User;
import com.liyang.reggie_takeout.mapper.UserMapper;
import com.liyang.reggie_takeout.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
