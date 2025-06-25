package com.atguigu.gulimail.product.web.service;

import javax.mail.MessagingException;

public interface MailService {
    String sendEmailCode(String email) throws MessagingException;
}
