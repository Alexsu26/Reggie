package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.entity.ShoppingCart;
import com.liyang.reggie_takeout.mapper.ShoppingCartMapper;
import com.liyang.reggie_takeout.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
