package com.liyang.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyang.reggie_takeout.dto.DishDto;
import com.liyang.reggie_takeout.entity.Dish;

public interface DishService extends IService<Dish> {

    // 新增菜品，并且插入菜品对应口味，需要操作dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品和其口味
    public DishDto getDishDtoById(Long id);

    // 更新菜品和口味信息
    public void updateWithFlavor(DishDto dishDto);
}
