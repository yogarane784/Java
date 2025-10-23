package multithreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * \ ReentrantLock What is it?
 * 
 * ReentrantLock is an explicit lock similar to synchronized but more flexible:
 * 
 * Can try to acquire the lock without blocking (tryLock())
 * 
 * Can acquire lock interruptibly (lockInterruptibly())
 * 
 * Can implement fairness policy (first-come-first-served)
 * 
 * "Reentrant" means the thread already holding the lock can acquire it again
 * without deadlocking.
 */
public class RentrantLocks {

	public static void main(String[] args) {

		Count counter = new Count();

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					counter.increment();
				}
			}
		};

		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t1.start();
		t2.start();

	}

}

class Count {
	private int count = 0;

	ReentrantLock lock = new ReentrantLock();

	public void increment() {

		lock.lock();

		try {
			count++;
			System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
		} finally {
			lock.unlock();
		}

	}
}

/*
 *
 * So you use it when:
 * 
 * You need timeout-based locking
 * 
 * You need fine-grained control
 * 
 * You need multiple condition variables
 * 
 * You want fair ordering
 */
