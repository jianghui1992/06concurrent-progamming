package com.edgewalk.accidence.signaling;

/**
 * Created by: edgewalk
 * 2018-08-20 21:42
 */
public class Consumer implements Runnable {
    private Apple apple;

    public Consumer(Apple apple) {
        this.apple = apple;
    }

    @Override
    public void run() {
        while (true){
            synchronized (apple){
                if (!apple.flag) {
                    try {//flag=false,说明还没有生产好苹果,那么阻塞当前线程,然后释放锁
                        System.out.println("阻塞了");
                        apple.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //走到这说明flag=true,有苹果了
                apple.take();//消费苹果
                apple.flag = false;//设置为false,表示消费者消费完了
                apple.notify();//唤醒producer生产苹果
            }
        }
    }
}
