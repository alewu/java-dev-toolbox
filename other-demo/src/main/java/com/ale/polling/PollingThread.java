package com.ale.polling;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
  *   这个轮询线程功能很简单，一直不停的轮询队列，一旦队列中有消息进入，就将它出队并调用display()方法输出内容。
  * @author alewu
  * @date 2020/8/15
  */
public class PollingThread extends Thread implements Runnable{
    public static Queue<Message> queue = new LinkedTransferQueue<>();

    @Override
    public void run() {
        while (true) {
            while (!queue.isEmpty()) {
                queue.poll().display();
            }
        }
    }
}