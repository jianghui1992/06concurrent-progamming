package com.edgewalk.accidence.methods;

/**
 * Created by: edgewalk
 * 2018-08-14 22:53
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("t3");
        });
        t1.start();
        t1.join();//main线程调用的该方法,main线程会进入wait方法,当t1运行结束时,才会被唤醒(notifyAll)
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }
}
