package com.atguigu.gulimail.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimail.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author zx
 * @email sunlightcs@gmail.com
 * @date 2023-05-21 18:48:54
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

