package com.atguigu.gulimail.product.service.impl;


import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimail.product.dao.CategoryDao;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listwithtree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        return categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0).peek(categoryEntity ->
                categoryEntity.setChildren(getChild(categoryEntity, categoryEntities))).collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> list) {
        //TODO 检查当前菜单是否被其他的地方引用
        baseMapper.deleteBatchIds(list);
    }

    private List<CategoryEntity> getChild(CategoryEntity category, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == category.getCatId()
        ).peek(menu -> menu.setChildren(getChild(menu, all))).collect(Collectors.toList());
    }
}