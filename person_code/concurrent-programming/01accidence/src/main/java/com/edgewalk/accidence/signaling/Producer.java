package com.edgewalk.accidence.signaling;

/**
 * Created by: edgewalk
 * 2018-08-20 21:32
 */
public class Producer implements Runnable {

    private Apple apple;

    public Producer(Apple apple) {
        this.apple = apple;
    }

    @Override
    public void run() {
        while (true) {
           synchronized (apple) {
                if (apple.flag) {
                    try {//flag为true,说明苹果还没有消费,就阻塞producer,并释放锁
                        apple.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //走到这,说明,flag=false,苹果被消费了
                apple.product();//生产一个苹果
                apple.flag = true;//设置为true,让消费者可以消费了
                apple.notify();//唤醒在resource锁上等待的对象
            }
        }
    }
}
