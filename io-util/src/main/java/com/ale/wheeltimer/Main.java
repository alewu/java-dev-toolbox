package com.ale.wheeltimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyTimerTask myTimerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(myTimerTask, 5, TimeUnit.SECONDS);
        int i = 1;
        while (myTimerTask.isFlag()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(i + "秒过去了");
            i++;

        }
    }
}
