package com.edgewalk.accidence.aqs;

import java.time.LocalTime;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: edgewalk
 * 2018-08-29 14:53
 */
public class ExchangerDemo {
	private static final Exchanger<String> exgr = new Exchanger<String>();
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		threadPool.execute(() -> {
			try {
				String A = "银行流水A"; // A录入银行流水数据
				/**
				 * 如果第一个线程先执行exchange()方法，它会一直等待第二个线程也执行exchange方法
				 * 当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
				 */
				String exchange = exgr.exchange(A); //获取不到其他线程的交换数据时,会一直阻塞
				printf("A和B数据是否一致："+A.equals(exchange)+"，A："+ A +"，B：" + exchange);
			} catch (InterruptedException e) {
			}
		});
		threadPool.execute(() -> {
			try {
				String B = "银行流水B"; // B录入银行流水数据
				String A = exgr.exchange(B);
				printf("AB是否一致:" + A.equals(B) +"，A录入的是："+A+"，B录入的是："+B);
			} catch (InterruptedException e) {
			}
		});
		threadPool.shutdown();
	}

	public static void printf(Object msg) {
		System.out.printf("%s - [%9s] - %s\n", LocalTime.now(), Thread.currentThread().getName(), msg);
	}

}
