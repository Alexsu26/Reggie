package com.liyang.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyang.reggie_takeout.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
