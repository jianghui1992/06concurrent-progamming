package com.edgewalk.accidence.create;

/**
 * Created by: edgewalk
 * 2018-08-14 11:49
 */
public class Demo2 {
	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();   //4,创建自定义类对象
		Thread t = new Thread(mr);        //5,将其当作参数传递给Thread的构造函数
		t.start();                            //6,开启线程
	}

	public void method1(){
		//匿名内部类+lamba 简写方式
		new Thread(() -> {
			System.out.println("run");
		}).start();
	}
}

class MyRunnable implements Runnable {  //1,自定义类实现Runnable接口
	public void run() {              //2,重写run方法
		System.out.println("run");//3,将要执行的代码,写在run方法中
	}
}
