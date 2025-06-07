package com.atguigu.gulimail.product.exception;

import com.atguigu.common.utils.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.error(400, message + "加品牌");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolation(ConstraintViolationException e) {
        return R.error(400, e.getMessage());
    }
}
