package com.edgewalk.accidence.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by: edgewalk
 * 2018-08-21 18:41
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			exec.execute(() -> {
				try {
					// 获取许可
					semp.acquire();
					System.out.println("Accessing: " + NO);
					Thread.sleep((long) (Math.random() * 10000));
					// 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
					semp.release();
				} catch (InterruptedException e) {
				}
			});
		}
		// 退出线程池
		exec.shutdown();
	}
}
