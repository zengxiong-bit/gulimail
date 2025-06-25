package com.atguigu.gulimail.product.dto;

import com.atguigu.gulimail.common.enue.ThirdPartyLoginType;
import lombok.Data;

@Data
public class ThirdPartyLoginDTO {
    private ThirdPartyLoginType type;
    private String response;
}

