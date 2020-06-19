package com.ale.bean;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
/**
  *
  * @author alewu
  * @date 2020/6/19
  */
public class SendMsgTask implements Runnable {
    @Override
    public void run() {
        int i = 0;
        try {
            i = ThreadLocalRandom.current().nextInt(1, 3);
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep" + i);
    }
}
