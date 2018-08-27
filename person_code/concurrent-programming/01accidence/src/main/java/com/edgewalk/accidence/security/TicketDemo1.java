package com.edgewalk.accidence.security;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by: edgewalk
 * 2018-08-19 19:05
 */
public class TicketDemo1 {
	public static void main(String[] args) {
		Ticket1 ticket = new Ticket1();
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}

}

@Slf4j
class Ticket1 implements Runnable {
	private int ticket = 100;

	public void run() {
		for (; ; ) {
			synchronized (Ticket1.class){
				if (ticket > 0) {
					if(true){
						throw new NumberFormatException();
					}
					log.info("出售第{}张票", ticket--);
				}
			}
		}
	}


}
