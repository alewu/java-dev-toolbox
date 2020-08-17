package com.ale;

public class App {
    public static void main(String[] args) {
        PollingThread pollingThread = new PollingThread();
        pollingThread.start();
        int i = 1;
        while (true) {
            PollingThread.queue.offer(new Message("新消息" + i));
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
