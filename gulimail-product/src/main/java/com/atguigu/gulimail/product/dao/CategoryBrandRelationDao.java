package com.atguigu.gulimail.product.dao;

import com.atguigu.gulimail.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联
 * 
 * @author zx
 * @email sunlightcs@gmail.com
 * @date 2023-05-15 17:09:34
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	
}
