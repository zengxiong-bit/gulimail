package com.atguigu.gulimail.order.dao;

import com.atguigu.gulimail.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zx
 * @email sunlightcs@gmail.com
 * @date 2023-05-21 18:55:54
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
