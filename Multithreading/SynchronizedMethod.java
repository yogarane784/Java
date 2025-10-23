// Synchronized method, static synchronized method, join(), start(), synchronized block


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

/*
 * join()
 * 
 * join() tells the current thread (in this case, the main thread) to wait until
 * the target thread (t1 or t2) has finished.
 * 
 * It’s like saying: “Main thread, hold on — don’t move ahead until t1
 * finishes.”
 * 
 * If you remove join(), the main thread might print counter.getCount() before
 * both threads finish, leading to incomplete results getting printed.
 * 
 */

/*
 * 
 * start()
 * 
 * When you call t1.start(), you are starting a new thread of execution.
 * 
 * The run() method (or the lambda body here) runs in parallel with the main
 * thread. DO NOT call run() directly — that would just run in the current
 * thread (not create a new one).
 * 
 */

/*
 * 
 * When you declare a method as:
 * 
 * public synchronized void increment() { count++; } and then call it like:
 * counter.increment();
 * 
 * this means: The lock is acquired on the object referenced by counter (i.e.,
 * the instance of the class where the synchronized method lives). Only one
 * thread at a time can hold that object’s lock
 * 
 */

/*
 * Static synchronized method class Logger 
 * {
 *  public static synchronized void log(String message) { 
 *  	System.out.println(Thread.currentThread().getName() + ": " + message); 
 *  }
 * }
 * 
 * A static synchronized method locks on the class object, not an instance.
 * 
 * So: Logger.log() can be called from anywhere.
 * Only one thread can execute "any static" synchronized method of Logger at a time. 
 * i.e if there are two static synchronized methods in the class Logger, 
 * while one thread is executing one, no other thread can execute other 
 * as the lock is acquired on the class itself
 */

//Equivalent using synchronized block
//Instead of locking the entire method, you can lock just a section:
//
//class Counter {
//    private int count = 0;
//
//    public void increment() {
//        synchronized (this) {
//            count++;
//        }
//    }
//}
//
//This gives you finer control — you can choose what part of the code to protect.
