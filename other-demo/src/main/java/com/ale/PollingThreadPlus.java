package com.ale;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Lock;

/**
 * 采用 wait/notify 优化 while 轮询
 *
 * @author alewu
 * @date 2020/8/15
 */
public class PollingThreadPlus extends Thread implements Runnable {
    public static Queue<Message> queue = new LinkedTransferQueue<>();

    @Override
    public void run() {
        while (true) {
            while (!queue.isEmpty()) {
                queue.poll().display();
            }
            //把队列中的消息全部打印完之后让线程阻塞
            synchronized (Lock.class) {
                try {
                    Lock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}