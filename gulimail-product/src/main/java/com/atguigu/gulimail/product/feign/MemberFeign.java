package com.atguigu.gulimail.product.feign;

import com.atguigu.common.utils.R;
import com.atguigu.gulimail.common.enue.ThirdPartyLoginType;
import com.atguigu.gulimail.product.dto.ThirdPartyLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member", url = "http://localhost:8000")
public interface MemberFeign {

    @RequestMapping("member/member/login/save")
    R loginSave(@RequestParam("thirdPartyLoginType") ThirdPartyLoginType thirdPartyLoginType,
                @RequestParam("response") String response);

}
