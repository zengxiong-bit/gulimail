package com.atguigu.gulimail.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：zx
 * Date ：2023-05-27-17:28
 * Description：
 */

@Service
@FeignClient("gulimail-coupon")
public interface CouponFeign {

    @RequestMapping("/coupon/coupon/fuck")
    public R couponlist();

    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupon();
}
