package com.edgewalk.accidence.create;

/**
 * Created by: edgewalk
 * 2018-08-14 11:48
 */
public class Demo1 {
	public static void main(String[] args) {
		MyThread mt = new MyThread();//4,创建自定义类的对象
		mt.start();                //5,开启线程
	}
}

class MyThread extends Thread {   //1,定义类继承Thread
	public void run() {        		//2,重写run方法
		System.out.println("run");//3,将要执行的代码,写在run方法中

	}
}
