package multithreading;

/**
 * In Java, the synchronized keyword is used to control access to a block of
 * code or a method, so that only one thread can execute it at a time.
 * 
 * It helps prevent race conditions — situations where multiple threads try to
 * read/write shared data simultaneously and corrupt it.
 */
public class SynchronizedMethod {

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Count = " + counter.getCount());
	}

}

class Counter {
	private int count = 0;

	/**
	 * increment() is a synchronized method.
	 * 
	 * When one thread is executing increment(), no other thread can execute any
	 * synchronized instance method like decrement() on the same object.
	 * 
	 * If increment() were not synchronized, the result could be less than 2000 due
	 * to race conditions. The statement count++ looks like one simple operation,
	 * but the CPU executes it in three steps:
	 * 
	 * Read the current value of count from memory (say, 5)
	 * 
	 * Add 1 to it → temporary result = 6
	 * 
	 * Write the result back to memory (store 6) These three steps are not atomic —
	 * meaning another thread can sneak in between them. In short these are now not
	 * happening sequentially but rather paralelly and thus its possible that before
	 * thread 1 could update the value from 5 to 6 , thread 2 read the value also as
	 * 5 and both tried to update the value to 6
	 */
	public void increment() {
		count++;
	}

	public synchronized void decrement() {
		count--;
	}

	public int getCount() {
		return count;
	}
}
