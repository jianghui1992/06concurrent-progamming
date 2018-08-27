package com.edgewalk.accidence.pool;

import java.util.concurrent.*;

/**
 * Created by: edgewalk
 * 2018-08-17 11:25
 */
public class MethodDemo {
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor =
				new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
		for (int i = 0; i < 2; i++) {
			executor.execute(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		//Thread.sleep(2000);
		System.out.println("线程池已执行和未执行的任务总数:"+executor.getTaskCount());
		System.out.println("已完成的任务数量:"+executor.getCompletedTaskCount());
		System.out.println("线程池当前的线程数量:"+executor.getPoolSize());
		System.out.println("线程池中正在执行任务的线程数:"+executor.getActiveCount());
		System.out.println("线程池曾经创建过的最大线程数:"+executor.getLargestPoolSize());
		executor.shutdown();
		System.out.println("调用shutdown方法");
		System.out.println("线程池是否shundown了:"+executor.isShutdown());
		System.out.println("线程池是否关闭了:"+executor.isTerminated());



	}
}
