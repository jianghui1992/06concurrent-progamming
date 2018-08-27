package com.edgewalk.accidence.methods;

/**
 * Created by: edgewalk
 * 2018-08-14 22:41
 */
public class DaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 1;
            for (; ; ) {
                System.out.println("hello,world+" + i++);
            }
        });
       // thread.setDaemon(true);   //设置为守护进程
        thread.start();
        Thread.sleep(1000);
    }
}
