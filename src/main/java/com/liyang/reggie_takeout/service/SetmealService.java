package com.liyang.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyang.reggie_takeout.dto.SetmealDto;
import com.liyang.reggie_takeout.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);
}
