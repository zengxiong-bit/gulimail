package com.atguigu.gulimail.coupon.dao;

import com.atguigu.gulimail.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zx
 * @email sunlightcs@gmail.com
 * @date 2023-05-21 18:29:26
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
