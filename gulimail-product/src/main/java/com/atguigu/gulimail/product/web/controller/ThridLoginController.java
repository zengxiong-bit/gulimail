package com.atguigu.gulimail.product.web.controller;

import cn.hutool.json.JSONUtil;
import com.atguigu.gulimail.common.enue.ThirdPartyLoginType;
import com.atguigu.gulimail.product.dto.ThirdPartyLoginDTO;
import com.atguigu.gulimail.product.feign.MemberFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@Slf4j

public class ThridLoginController {

    @Value("${gitee.client-id}")
    private String clientId;

    @Value("${gitee.client-secret}")
    private String clientSecret;

    @Value(("${gitee.redirect-uri}"))
    private String redirectUri;

    @Autowired
    private MemberFeign memberFeign;


    private final RestTemplate restTemplate = new RestTemplate();



    @RequestMapping("gitee/callback")
    public String callback(@RequestParam("code") String code){
        log.info("Received code: {}", code);

        // 发送请求获取token
        String tokenUrl = "https://gitee.com/oauth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("client_secret", clientSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        Map<String, Object> tokenResponse = restTemplate.postForObject(tokenUrl, request, Map.class);
        assert tokenResponse != null;
        String accessToken = (String) tokenResponse.get("access_token");

        // Step 2: 获取用户信息
        String userUrl = "https://gitee.com/api/v5/user?access_token=" + accessToken;
        String response = restTemplate.getForObject(userUrl, String.class);


        // 封装member对象

        memberFeign.loginSave(ThirdPartyLoginType.GITEE, response);

        return "index";
    }
}
