package com.atguigu.gulimail.thirdpart.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "myapp")
public class BeanConfig {
    private String name;
    private boolean enabled;
    private int timeout;
    private List<String> servers;
}
