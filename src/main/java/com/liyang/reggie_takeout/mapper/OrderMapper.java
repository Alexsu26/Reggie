package com.liyang.reggie_takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyang.reggie_takeout.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
