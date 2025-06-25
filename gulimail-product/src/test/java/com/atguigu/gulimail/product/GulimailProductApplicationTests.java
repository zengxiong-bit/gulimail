package com.atguigu.gulimail.product;


import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;


@SpringBootTest
class GulimailProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("苹果");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("redis", "hello!");

        String redis = ops.get("redis");

        System.out.println(redis);

        new Thread(() -> System.out.println("当前线程" + Thread.currentThread().getName())).start();

        CompletableFuture.supplyAsync(() -> {
            return "数据";
        }).thenAccept(data -> {
            System.out.println("处理：" + data);
        });
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "数据";
        });
        System.out.println(stringCompletableFuture.get());


    }

    @Test
    void test1() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                4,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "线程池创建  😶‍🌫️");
        });
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "线程池创建  😶‍🌫️");
        });
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "线程池创建  😶‍🌫️");
        });
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "线程池创建  😶‍🌫️");
        });
    }

    @Test
    void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("异步执行完毕");
            return "异步执行完毕";
        });
        System.out.println(Thread.currentThread().getName() + "无阻塞");
        System.out.println(Thread.currentThread().getName() + "阻塞" + future.get());
    }

    @Test
    void test3() throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("任务1线程：" + Thread.currentThread().getName());
//            return 10;
//        });
//
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                sleep(1500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("任务2线程：" + Thread.currentThread().getName());
//            return 20;
//        });
//
//        // 两个任务完成后，将结果相加
//        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
//            System.out.println("合并线程：" + Thread.currentThread().getName());
//            return result1 + result2;
//        });
//
//        System.out.println("最终结果：" + combinedFuture.get());


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务1线程" + Thread.currentThread().getName());
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "任务1完成";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务2线程" + Thread.currentThread().getName());
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "任务2完成";
        });

        CompletableFuture<String> resultFuture = future2.applyToEither(future1, result -> {
            System.out.println("最快完成的线程：" + Thread.currentThread().getName());
            return result + " - 处理结果";
        });

        System.out.println(resultFuture.get());


    }


}
