package com.atguigu.gulimail.product.web.controller;

import cn.hutool.core.lang.UUID;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redisson")
public class IndexController {

    private static final String SEMAPHORE_KEY = "park";
    private static final int MAX_PARKING_SPOTS = 5;
    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }




    @GetMapping("/park")
    public String park() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_KEY);

        boolean success = semaphore.tryAcquire(); // 非阻塞尝试获取一个车位

        if (success) {
            return "✅ 进入成功，车位剩余：" + semaphore.availablePermits();
        } else {
            return "❌ 停车场已满，请稍后再试";
        }
    }

    /**
     * 模拟离开停车场
     */
    @GetMapping("/go")
    public String go() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_KEY);

        // 当前剩余许可
        int available = semaphore.availablePermits();

        if (available < MAX_PARKING_SPOTS) {
            semaphore.release(); // 释放一个车位
            return "🚗 离开成功，车位剩余：" + (available + 1);
        } else {
            return "⚠️ 当前车位已满，不需要释放";
        }
    }

    /**
     * 查看剩余车位
     */
    @GetMapping("/status")
    public String status() {
        RSemaphore semaphore = redissonClient.getSemaphore(SEMAPHORE_KEY);
        return "📊 当前剩余车位：" + semaphore.availablePermits();
    }

    @RequestMapping("/write")
    public String write(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("my-lock");
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        try {
            Thread.sleep(10000);
            return "写入成功";
        }catch (Exception e){
            return "写入失败";
        } finally {
            rLock.unlock();
        }

    }
    @RequestMapping("/read")
    public String read(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("my-lock");
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        try {
            Thread.sleep(10000);
            return "读取成功";
        }catch (Exception e){
            return "读取失败";
        } finally {
            rLock.unlock();
        }

    }
}

