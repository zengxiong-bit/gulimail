package com.atguigu.gulimail.thirdpart.controller;


import com.atguigu.common.utils.R;
import com.atguigu.gulimail.thirdpart.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class PropertiesController {

    @Autowired
    PropertiesService propertiesService;


    @RequestMapping("/test")
    public R getConfig(){
        return propertiesService.testAutoProperties();
    }
}
