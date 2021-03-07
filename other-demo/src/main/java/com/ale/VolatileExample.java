package com.ale;

public class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();

        new Thread(() -> volatileExample.writer()).start();
        new Thread(() -> volatileExample.reader()).start();


    }

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v == true) {
            // 这里 x 会是多少呢？
            System.out.println(x);
        }
    }
}
