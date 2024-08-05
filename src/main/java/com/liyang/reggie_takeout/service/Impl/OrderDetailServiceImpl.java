package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.entity.OrderDetail;
import com.liyang.reggie_takeout.mapper.OrderDetailMapper;
import com.liyang.reggie_takeout.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
