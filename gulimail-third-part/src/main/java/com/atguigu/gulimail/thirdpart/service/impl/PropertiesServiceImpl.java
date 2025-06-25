package com.atguigu.gulimail.thirdpart.service.impl;


import com.atguigu.common.utils.R;
import com.atguigu.gulimail.thirdpart.config.BeanConfig;
import com.atguigu.gulimail.thirdpart.service.PropertiesService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PropertiesServiceImpl implements PropertiesService {

    private final BeanConfig beanConfig;


    public PropertiesServiceImpl(BeanConfig beanConfig) {
        this.beanConfig = beanConfig;
    }

    @Override
    public R testAutoProperties() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("App Name: ", beanConfig.getName());
        hashMap.put("Enabled: ", beanConfig.isEnabled());
        hashMap.put("Timeout: ", beanConfig.getTimeout());
        hashMap.put("Servers: ", beanConfig.getServers());

        return R.ok().put("data", hashMap);
    }
}
