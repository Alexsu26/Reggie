package com.liyang.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyang.reggie_takeout.entity.Orders;

public interface OrderService extends IService<Orders> {

    public void submit(Orders orders);
}
