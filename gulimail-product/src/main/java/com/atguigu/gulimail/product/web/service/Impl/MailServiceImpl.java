package com.atguigu.gulimail.product.web.service.Impl;

import com.atguigu.gulimail.product.web.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public String sendEmailCode(String email) throws MessagingException {

        // 1. 生成验证码
        String code = String.valueOf(new Random().nextInt(899999) + 100000);

        String key = "email::code" + email;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))){
            throw new MessagingException("验证码已发送，请稍后再试");
        }else {
            stringRedisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
            Context context = new Context();
            context.setVariable("code", code); // 注入变量

            String htmlContent = templateEngine.process("sms", context);


            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("1908151641@qq.com");
            helper.setTo(email);
            helper.setSubject("【注册验证码】");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        }
        return code;
    }
}
