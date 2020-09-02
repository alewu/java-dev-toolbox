package com.ale;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/9/2
 */
class AllOfTest {
    @Test
    void test() throws InterruptedException {
        List<CompletableFuture<String>> comlist = new ArrayList<>();
        int size = 3;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int res = i;
            CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(System.currentTimeMillis() + ":" + res + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return res + "";
            });
            f1.whenCompleteAsync((x, y) -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(System.currentTimeMillis() + ":" + x + "回调执行完成");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            comlist.add(f1);
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(comlist.toArray(new CompletableFuture[size]));
        //阻塞，直到所有任务结束。任务complete就会执行,handler里面不一定会执行..
        System.out.println(System.currentTimeMillis() + ":阻塞");
        all.join();
        System.out.println(System.currentTimeMillis() + ":阻塞结束");
        countDownLatch.await();
        System.out.println("回调都结束...");
    }
}
