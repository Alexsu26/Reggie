package com.liyang.reggie_takeout.controller;

import com.liyang.reggie_takeout.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/orderDetail")
@RestController
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
}
