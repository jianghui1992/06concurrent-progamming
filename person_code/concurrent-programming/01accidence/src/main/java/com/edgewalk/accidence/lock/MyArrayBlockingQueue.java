package com.edgewalk.accidence.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by: edgewalk
 * 2018-08-21 15:27
 */
public class MyArrayBlockingQueue<E> {

	/** 保存队列中的元素 */
	final Object[] items;

	/** items index for next take, poll, peek or remove */
	int takeIndex;

	/** items index for next put, offer, or add */
	int putIndex;

	/** 数组中当前元素的个数 */
	int count;

	/** 锁 */
	final ReentrantLock lock;

	/**
	 * Condition可以分组,用户唤醒/等待不同组的对象
	 */
	/** Condition for waiting takes */
	private final Condition empty;

	/** Condition for waiting puts */
	private final Condition full;

	public MyArrayBlockingQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		this.items = new Object[capacity];
		lock = new ReentrantLock();
		empty = lock.newCondition();//Condition是绑定在锁上面的
		full =  lock.newCondition();
	}


	/**
	 * 1.如果集合满了,就调用full.await()方法使对应的线程释放锁，并且使调用线程阻塞。
	 *   直到其他线程调用了take()方法，并调用了full.signal()时，该请求线程会被唤醒,代码继续往下执行
	 * 2. 若集合不满，则添加新元素，并且通过empty.signal()唤醒等待取数据的线程
	 */
	public void put(E e) throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly(); //线程安全的
		try {
			while (count == items.length)//队列满了
				full.await();//对应的线程(调用put方法的所有线程)释放锁，并且使调用线程阻塞
			enqueue(e);
		} finally {
			lock.unlock();
		}
	}
	private void enqueue(E x) {
		final Object[] items = this.items;
		items[putIndex] = x;
		if (++putIndex == items.length)
			putIndex = 0;
		count++;
		empty.signal(); //添加元素到对内中,唤醒empty上等待的线程
	}

	/**
	 * 1.如果集合空了,就调用empty.await()方法使对应的线程释放锁，并且使调用线程阻塞。
	 *  直到其他线程调用了put()方法，并调用了empty.signal()时，该请求线程会被唤醒,代码继续往下执行
	 *
	 * 2.若集合有元素，则获取元素，并且通过full.signal();唤醒等待添加数据的线程
	 */
	public E take() throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (count == 0)//对内为空
				empty.await();//对应的线程(调用take方法的所有线程)释放锁,并且使调用线程阻塞
			return dequeue();
		} finally {
			lock.unlock();
		}
	}

	private E dequeue() {
		final Object[] items = this.items;
		@SuppressWarnings("unchecked")
		E x = (E) items[takeIndex];
		items[takeIndex] = null;
		if (++takeIndex == items.length)
			takeIndex = 0;
		count--;
		full.signal();//出队完成,唤醒在full上等待的线程
		return x;
	}
}
