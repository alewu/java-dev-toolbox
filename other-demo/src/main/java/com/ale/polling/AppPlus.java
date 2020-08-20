package com.ale.polling;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
@Slf4j
public class AppPlus {
    public static void main(String[] args) {
        PollingThreadPlus pollingThread = new PollingThreadPlus();
        pollingThread.start();
        int i = 1;
        while (true) {
            PollingThreadPlus.queue.offer(new Message("msg: " + i));
            log.info("main thread produce msg {}", i);
            i++;
            //有消息入队后激活轮询线程
            synchronized (Lock.class) {
                Lock.class.notify();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
