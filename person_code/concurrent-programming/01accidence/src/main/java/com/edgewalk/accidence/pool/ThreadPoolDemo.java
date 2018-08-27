package com.edgewalk.accidence.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by: edgewalk
 * 2018-08-17 11:39
 */
@Slf4j
public class ThreadPoolDemo {
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor =
				new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
		for (int i = 0; i < 10; i++) {
			final int flag = i;
			executor.execute(() -> {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HashSet<Integer> set = new HashSet<>();
				for (int j = 0; j < 1000000; j++) {
					set.add(j);
				}

				log.info("flag:{},size,{}", flag, set.size());
			});
		}
		Thread.sleep(1000);
		executor.shutdown();
		log.info("send shutdownNow()");
	}
}
