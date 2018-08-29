package com.edgewalk.accidence.aqs;

import java.time.LocalTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by: edgewalk
 * 2018-08-29 11:55
 */
public class CyclicBarrierDemo2 {
	static CyclicBarrier c = new CyclicBarrier(2);
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 4; i++) {
			new Thread(() -> {
				try {
					printf("start");
					c.await();
					printf("end");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
			Thread.sleep(2000);
		}
	}

	public static void printf(Object msg) {
		System.out.printf("%s - [%9s] - %s\n", LocalTime.now(), Thread.currentThread().getName(), msg);
	}
}
