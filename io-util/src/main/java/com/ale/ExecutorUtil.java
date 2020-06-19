package com.ale;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public final class ExecutorUtil {

    public static Executor getExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread img" + "-%d")
                .setDaemon(true).build();

        return new ThreadPoolExecutor(5, 10,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10), threadFactory);
    }


    public static Executor getUserExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("user " + "-%d")
                .setDaemon(true).build();

        return new ThreadPoolExecutor(5, 10,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(1000), threadFactory);
    }

    public static Executor getSendMsgExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("send msg" + "-%d")
                .setDaemon(true).build();

        return new ThreadPoolExecutor(8, 10,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10), threadFactory);
    }
}
