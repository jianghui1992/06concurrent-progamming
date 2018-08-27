package com.edgewalk.accidence.create;

import java.util.concurrent.*;

/**
 * Created by: edgewalk
 * 2018-08-14 11:51
 */
public class Demo3 {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService= Executors.newFixedThreadPool(1);
		Future<String> future=executorService.submit(new MyCallable());
		String s = future.get();
		System.out.println(s);
		executorService.shutdown();//会在所有任务完成的情况下,才会关闭线程池
	}
}

class MyCallable implements Callable { //1,自定义类实现Callable接口
	@Override
	public String call() {
		int a = 1;
		int b = 2;
		System.out.println(a + b);
		return "执行结果:" + (a + b);
	}

}
