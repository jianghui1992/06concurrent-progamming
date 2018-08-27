package com.edgewalk.accidence.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by: edgewalk
 * 2018-08-16 16:00
 */
@Slf4j
public class CreatePoolDemo {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		//延迟一秒后,当前一个任务完成后,延迟3秒在运行任务
		service.scheduleWithFixedDelay(() -> log.info("scheduleWithFixedDelay"), 1, 3, TimeUnit.SECONDS);
		//延迟一秒后,每隔3s,固定执行任务。
		service.scheduleAtFixedRate(() -> log.info("scheduleWithFixedDelay"), 1, 3, TimeUnit.SECONDS);
	}

}
