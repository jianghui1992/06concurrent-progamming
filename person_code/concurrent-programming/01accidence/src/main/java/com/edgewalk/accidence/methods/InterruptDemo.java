package com.edgewalk.accidence.methods;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by: edgewalk
 * 2018-08-17 16:21
 */
@Slf4j
public class InterruptDemo {
	private static int i;

	public static void main(String[] args) throws InterruptedException {
		method4();
		Thread.interrupted();
	}

	public static void method1() throws InterruptedException {
		Thread thread = new Thread(() -> {
			//thread.isInterrupted():获取线程的中断标记的值
			while (!Thread.currentThread().isInterrupted()) {
				i++;
			}
			log.info("Num:" + i);
		}, "interruptDemo");
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt();
	}

	public static void method2() throws InterruptedException {
		Thread thread = new Thread(() -> {
			while (true) {
				Boolean ii = Thread.currentThread().isInterrupted();

				if (ii) {
					log.info("before:" + ii);
					Thread.interrupted();//对线程进行复位，中断标识为false
					log.info("after:" + Thread.currentThread().isInterrupted());
				}
			}
		});
		thread.start();
		log.info("thread1:"+thread.isInterrupted());
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt();//设置中断标识,中断标识为 true

		log.info("thread2:"+thread.isInterrupted());
	}

	public static void method3() throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.info("exception:{}",e.getLocalizedMessage());
			}
		});
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		log.info(String.valueOf(thread.isInterrupted()));
		thread.interrupt();
		//TimeUnit.SECONDS.sleep(1);
		log.info(String.valueOf(thread.isInterrupted()));
		Thread.interrupted();
		log.info(String.valueOf(thread.isInterrupted()));

	}


	public static void method4() throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.info("exception:{}",e.getLocalizedMessage());
			}
		});
		thread.start();
		log.info("1,{}",String.valueOf(thread.isInterrupted()));//false
		thread.interrupt();
		log.info("2,{}",String.valueOf(thread.isInterrupted()));//true
		TimeUnit.SECONDS.sleep(1);
		log.info("3,{}",String.valueOf(thread.isInterrupted()));//false
	}
}
