package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.entity.Dish;
import com.liyang.reggie_takeout.mapper.DishMapper;
import com.liyang.reggie_takeout.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
