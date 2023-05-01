package com.hospital.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.controller.front.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
