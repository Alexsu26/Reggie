package com.liyang.reggie_takeout.controller;

import com.liyang.reggie_takeout.common.R;
import com.liyang.reggie_takeout.entity.Orders;
import com.liyang.reggie_takeout.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("submit... ");

        orderService.submit(orders);
        return R.success("下单成功！");
    }
}
