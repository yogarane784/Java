package multithreading;

import java.util.concurrent.Semaphore;

/**
 * Semaphores What is it?
 * 
 * A Semaphore is a concurrency utility that controls access to a limited number
 * of resources.
 * 
 * You can think of it as a counter:
 * 
 * acquire() decreases the counter
 * release() increases the counter
 * 
 * If the counter is 0, threads trying to acquire will wait until a permit is
 * available.
 * 
 * When to use?
 * 
 * Limiting the number of threads accessing a pool/resource (e.g., DB
 * connections, printers).
 */

public class Semaphores {
	
	public static void main(String[] args) {
		
		 Semaphore semaphore = new Semaphore(2);
		
		for(int i=0;i<5;i++) {
			
			// We are here creating 5 threads and passing the same semaphore object 
			 new Thread(new ResourceUser(semaphore), "Thread-" + i).start(); 
			 
		}
		
	}

}

class ResourceUser implements Runnable {

	private Semaphore semaphore;

	public ResourceUser(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " acquired lock");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " acquired lock");
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
