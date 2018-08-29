package com.edgewalk.accidence.aqs;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by: edgewalk
 * 2018-08-21 18:41
 */
@Slf4j
public class SemaphoreDemo {
	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			int finalIndex = index;
			exec.execute(new Thread(() -> {
				try {
					// 获取许可
					semp.acquire();
					printf("Accessing: " + finalIndex);
					Thread.sleep(2000);
					semp.release();
				} catch (InterruptedException e) {
				}
			}));
		}
		// 退出线程池
		exec.shutdown();
	}

	public static void printf(String msg) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = formatter.format(LocalDateTime.now());
		System.out.printf("%s - [%9s] - %s\n", formatDate, Thread.currentThread().getName(), msg);
	}
}
