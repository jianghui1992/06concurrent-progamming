package com.edgewalk.accidence.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by: edgewalk
 * 2018-08-21 11:35
 */
public class LockInterruptiblyDemo {
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		LockInterruptiblyDemo bc = new LockInterruptiblyDemo();

		Thread t0 = new Thread(() -> bc.bFuction());
		Thread t1 = new Thread(() -> bc.bFuction());
		String tName = Thread.currentThread().getName();
		printf("启动Thread-0！");
		t0.start();
		Thread.sleep(5000);
		printf("启动Thread-1");
		t1.start();
		Thread.sleep(5000);
		printf("Thread-1被阻塞了,中断Thread-1");
		t1.interrupt();
	}

	public static void printf(String msg) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = formatter.format(LocalDateTime.now());
		System.out.printf("%s - [%9s] - %s\n", formatDate, Thread.currentThread().getName(), msg);
	}

	// 业务方法
	public void bFuction()  {
		String tName = Thread.currentThread().getName();
		printf("尝试获取锁..........");

		//**获取锁不能再下面try里面,不然会导致意外进入finally,释放不是别人的锁**
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			printf("线程被中断:"+e.toString());
			return;
		}

		try {
			printf("获取到了锁..........");
			printf("执行任务开始.......");
			Thread.sleep(20000);
			printf("执行任务完成.......");
		} catch (Exception e) {

			printf("执行任务异常");
			e.printStackTrace();
		} finally {
			lock.unlock();
			printf("释放了锁");
		}
	}
}