package com.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hospital.controller.front.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
