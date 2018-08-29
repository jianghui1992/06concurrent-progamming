package com.edgewalk.accidence.aqs;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by: edgewalk
 * 2018-08-29 11:55
 */
public class CyclicBarrierDemo1 {
	static CyclicBarrier c = new CyclicBarrier(1);
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			try {
				c.await();
			} catch (Exception e) {
				System.out.println(c.isBroken());//true,线程为中断就返回true
			}
		});
		thread.start();
		thread.interrupt();
	}
}
