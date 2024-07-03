package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.entity.DishFlavor;
import com.liyang.reggie_takeout.mapper.DishFlavorMapper;
import com.liyang.reggie_takeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
