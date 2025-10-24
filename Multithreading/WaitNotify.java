package multithreading;

/**
 * wait / notify / notifyAll These are methods on every Java Object, defined in
 * java.lang.Object — not in Thread! They are used for communication between
 * threads, typically for coordination like the Producer–Consumer problem.
 * 
 * Key idea:
 * 
 * One thread waits for a condition to become true.
 * 
 * Another thread notifies when that condition changes.
 * 
 * The rule you must never forget
 * 
 * These methods must be called inside a synchronized block or method on the
 * same object.
 * 
 * Otherwise, you get:
 * 
 * IllegalMonitorStateException
 * 
 * 
 * Why? Because they use the monitor lock of the object to coordinate — same
 * lock used by synchronized.
 * 
 * wait() Releases the lock and waits until notified notify() Wakes up one
 * waiting thread notifyAll() Wakes up all waiting threads
 */
public class WaitNotify {

	public static void main(String[] args) {

		Printer printer = new Printer();

		Thread t1 = new Thread(() -> {
			for (int i = 1; i < 10; i += 2) {
				printer.printOdd(i);
			}
		}, "OddThread");

		Thread t2 = new Thread(() -> {
			for (int i = 2; i <= 10; i += 2) {
				printer.printEven(i);
			}
		}, "EvenThread");

		t1.start();
		t2.start();
	}

}

class Printer {
	private boolean isOddTurn = true; // start with odd

	public synchronized void printOdd(int number) {
		while (!isOddTurn) {
			try {
				wait(); // wait while its odd's turn

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName() + " -> " + number);
		isOddTurn = false;
		notify(); // wake up even thread
	}

	public synchronized void printEven(int number) {
		while (isOddTurn) {
			try {
				wait(); // wait while its even's turn

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName() + " -> " + number);
		isOddTurn = true;
		notify(); // wake up odd thread
	}

}
