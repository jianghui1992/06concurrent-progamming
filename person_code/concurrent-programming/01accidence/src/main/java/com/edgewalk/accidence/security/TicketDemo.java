package com.edgewalk.accidence.security;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by: edgewalk
 * 2018-08-19 19:05
 */
public class TicketDemo {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}

}

@Slf4j
class Ticket implements Runnable {
	private int ticket = 100;
	private Lock lock = new ReentrantLock();//1.创建锁对象

	public void run() {
		for (; ; ) {
			lock.lock(); //2.上锁
			try {
				if (ticket > 0) {
					log.info("出售第{}张票", ticket--);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*
				  3.写在finally中,会一定释放锁
				  当出现异常时,其他线程可以继续运行
				 */
				lock.unlock();
			}
		}
	}


}
