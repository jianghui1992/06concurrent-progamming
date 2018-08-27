package com.edgewalk.accidence.aqs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * Created by: edgewalk
 * 2018-08-21 17:51
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
//		CountDownLatch latch = new CountDownLatch(2);//计数器为2
//		new Thread(() -> {
//			doWork();
//			latch.countDown(); //计数器减1
//		}).start();
//		new Thread(() -> {
//			doWork();
//			latch.countDown();
//		}).start();
//		/**
//		 * 当主线程执行到这的时候,现在一共是有3个线程在同时执行
//		 * 当主线程执行了latch.await()方法的时候,就会阻塞主线程,只到CountDownLatch的计数器变成0
//		 * 而当其他线程执行了latch.countDown()方法后,才会把计数器减一.
//		 * 所以这个时候,主线程就会等待计数器变成0后再执行
//		 */
//		latch.await();
//		printf("all work done ");

		Thread thread1 = new Thread(() -> {
			doWork();
		});
		Thread thread2 = new Thread(() -> {
			doWork();
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		printf("all work done ");
	}

	private static void doWork() {
		try {
			printf("开始工作...");
			Thread.sleep(2000);
			printf("完成工作...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void printf(String msg) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = formatter.format(LocalDateTime.now());
		System.out.printf("%s - [%9s] - %s\n", formatDate, Thread.currentThread().getName(), msg);
	}
}
