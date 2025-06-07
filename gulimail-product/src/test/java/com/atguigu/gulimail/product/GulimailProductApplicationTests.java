package com.atguigu.gulimail.product;


import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimailProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("苹果");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("redis", "hello!");

        String redis = ops.get("redis");

        System.out.println(redis);
    }



}
