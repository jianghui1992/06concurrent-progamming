package com.edgewalk.accidence.methods;

/**
 * Created by: edgewalk
 * 2018-08-17 17:19
 */
public class ValidateDemo {
	private volatile static boolean stop = false;

	public static void main(String[] args) throws InterruptedException {
		method2();

	}

	private static void method1() throws InterruptedException {
		Thread thread = new Thread(() -> {
			int i = 0;
			while (!stop) {
				i++;
			}
		});
		thread.start();
		System.out.println("begin start thread");
		Thread.sleep(1000);
		stop = true;
	}

	private static void method2() throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				while (!Thread.currentThread().isInterrupted()) {
					Thread.sleep(100); // 休眠100ms
					//do something
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		System.out.println("begin start thread");
		Thread.sleep(1000);
		thread.interrupt();
	}
}
