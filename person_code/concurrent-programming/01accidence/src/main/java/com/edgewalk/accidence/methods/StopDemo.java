package com.edgewalk.accidence.methods;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by: edgewalk
 * 2018-08-17 16:13
 */
@Slf4j
public class StopDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("run..");
			}
		});
		thread.start();
		TimeUnit.SECONDS.sleep(2);
//		thread.stop();
//		thread.interrupt();
	}
}
