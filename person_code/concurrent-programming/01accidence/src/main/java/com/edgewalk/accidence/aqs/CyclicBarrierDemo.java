package com.edgewalk.accidence.aqs;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by: edgewalk
 * 2018-08-29 11:55
 */
public class CyclicBarrierDemo implements Runnable {
	/**
	 * 创建4个屏障，处理完之后执行当前类的run方法
	 */
	private CyclicBarrier c = new CyclicBarrier(4, this);
	/**
	 * 假设只有4个sheet，所以只启动4个线程
	 */
	private Executor executor = Executors.newFixedThreadPool(4);
	/**
	 * 保存每个sheet计算出的银流结果
	 */
	private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
		cyclicBarrierDemo.count();
	}

	private void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(() -> {
				// 计算当前sheet的银流数据，计算代码省略
				sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
				// 银流计算完成，插入一个屏障,当屏障达到构造方法中设置的4后,会执行run方法
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public void run() {
		int result = 0;
		// 汇总每个sheet计算出的结果
		for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
			result += sheet.getValue();
		}
		// 将结果输出
		sheetBankWaterCount.put("result", result);
		System.out.println(result);
	}
}
