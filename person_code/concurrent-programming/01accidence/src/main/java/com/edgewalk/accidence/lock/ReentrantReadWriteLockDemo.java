package com.edgewalk.accidence.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用
 * Created by: edgewalk
 * 2018-08-21 16:33
 */
public class ReentrantReadWriteLockDemo {
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private Object data = null;//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。

	public static void main(String[] args) throws InterruptedException {
		ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
		new Thread(() -> demo.write(new Random().nextInt(100))).start();
		Thread.sleep(1000);
		new Thread(demo::read).start();
;
	}

	public static void printf(String msg) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = formatter.format(LocalDateTime.now());
		System.out.printf("%s - [%9s] - %s\n", formatDate, Thread.currentThread().getName(), msg);
	}

	/**
	 * 写操作,用写锁,来锁定
	 */
	public void write(Object data) {
		rwl.writeLock().lock();
		printf("获取到了写锁.....");
		try {
			Thread.sleep(3000);
			this.data = data;
			printf("写入数据:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			printf("释放写锁.....");
			rwl.writeLock().unlock();
		}
	}

	/**
	 * 读操作,读锁,来锁定
	 */
	public void read() {
		rwl.readLock().lock();
		printf("获取到了读锁.....");
		try {
			Thread.sleep(3000);
			printf("读取数据:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			rwl.readLock().unlock();
		}
	}
}
