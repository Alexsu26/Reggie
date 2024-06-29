package com.liyang.reggie_takeout.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyang.reggie_takeout.common.CustomException;
import com.liyang.reggie_takeout.entity.Category;
import com.liyang.reggie_takeout.entity.Dish;
import com.liyang.reggie_takeout.entity.Setmeal;
import com.liyang.reggie_takeout.mapper.CategoryMapper;
import com.liyang.reggie_takeout.service.CategoryService;
import com.liyang.reggie_takeout.service.DishService;
import com.liyang.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;

    @Override
    public void remove(Long id) {
        // 检查菜品是否关联
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("已与菜品关联，不能删除！");
        }

        // 检查套餐是否关联
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            throw new CustomException("已与套餐关联，不能删除！");
        }

        // 删除
        super.removeById(id);
    }
}
