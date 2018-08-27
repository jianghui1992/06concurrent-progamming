package com.edgewalk.accidence.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by: edgewalk
 * 2018-08-21 20:56
 */
public class StampedLockDemo {

    private final StampedLock lock = new StampedLock();
    private double x, y;

    //写入数据,和原来一样
    void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    //下面看看乐观读锁案例
    double distanceFromOrigin() { // A read-only method
        long stamp = lock.tryOptimisticRead(); //获得一个读乐观锁
        double currentX = x, currentY = y;  //将两个字段读入本地局部变量
        if (!lock.validate(stamp)) { //检查发出乐观读锁后同时是否有其他写锁发生？
            stamp = lock.readLock();  //如果没有，我们再次获得一个读悲观锁
            try {
                currentX = x; // 将两个字段读入本地局部变量
                currentY = y; // 将两个字段读入本地局部变量
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    //下面是悲观读锁案例
    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = lock.readLock();
        try {
            while (x == 0.0 && y == 0.0) { //循环，检查当前状态是否符合
                long ws = lock.tryConvertToWriteLock(stamp); //将读锁转为写锁
                if (ws != 0L) { //这是确认转为写锁是否成功
                    stamp = ws; //如果成功 替换票据
                    x = newX; //进行状态改变
                    y = newY;  //进行状态改变
                    break;
                } else { //如果不能成功转换为写锁
                    lock.unlockRead(stamp);  //我们显式释放读锁
                    stamp = lock.writeLock();  //显式直接进行写锁 然后再通过循环再试
                }
            }
        } finally {
            lock.unlock(stamp); //释放读锁或写锁
        }
    }
}

