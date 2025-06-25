package com.atguigu.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.gulimail.product.entity.AttrEntity;
import com.atguigu.gulimail.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;


/**
 * 商品属性
 *
 * @author zx
 * @email sunlightcs@gmail.com
 * @date 2023-05-15 18:06:07
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    // @RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrEntity attr) {
        attrService.save(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "attr", key = "'entity'")
    @RequestMapping("/update")
    // @RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

    @RequestMapping("/selectAll")
    public List<AttrEntity> update() {
        RMapCache<String, String> attrListCatch = redissonClient.getMapCache("attr");
        String entities = attrListCatch.computeIfAbsent("attrEntities", (k) -> {
            System.out.println("🥶🥶 [数据库查询] 正在查询 ........");
            List<AttrEntity> attrEntities = attrService.selectAll();
            return JSONUtil.toJsonStr(attrEntities);
        });
        JSONArray jsonArray = JSONUtil.parseArray(entities);

        return JSONUtil.toList(jsonArray, AttrEntity.class);
    }

    @RequestMapping("/cache")
    @Cacheable(value = "attr", key = "'entity'")
    public List<AttrEntity> cache() {
            System.out.println("🥶🥶 [数据库查询] 正在查询 ........");
            return attrService.selectAll();

    }

    @RequestMapping("/cachePut")
    @CachePut(value = "attr", key = "'entity'")
    public List<AttrEntity> cachePut() {
        System.out.println("🥶🥶 [数据库查询] 正在查询 ........");
        return attrService.selectAll();

    }

}
