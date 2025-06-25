package com.atguigu.gulimail.product.web.controller;


import cn.hutool.core.util.StrUtil;
import com.atguigu.gulimail.product.web.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@Controller
public class MailContrller {

    @Autowired
    MailService mailService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/sendCode")
    public String sendCode(@RequestParam String email, Model model) throws MessagingException {
        String code = mailService.sendEmailCode(email);
        model.addAttribute("code", code);
        return "verify";
    }

    @RequestMapping("/verifyCode")
    public String verifyCode(@RequestParam String email, @RequestParam String code){
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey("email::code" + email))){
            return "fail";
        }else {
            String codeV = stringRedisTemplate.opsForValue().get("email::code" + email);
            assert codeV != null;
            if (codeV.equals(code)){
                return "success";
            }
        }
        return "success";
    }
}
