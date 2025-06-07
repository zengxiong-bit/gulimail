package com.atguigu.gulimail.gateway.controller;

import com.atguigu.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zx
 * Date ：2024-04-09-12:15
 * Description：
 */
@RefreshScope
@RestController
@RequestMapping("gateway")
public class TestController {

    @Value("${gateway.name}")
    String name;
    @Value("${gateway.age}")
    Integer age;

    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name", name);
    }
}
