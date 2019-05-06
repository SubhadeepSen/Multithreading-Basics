package basicsOfMultithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBasic {

	private static Lock lock = new ReentrantLock();
	private static int counter = 0;

	// lock is not used then the result will be inconsistent, remove lock and check
	// result, after execution of two threads,result should be 20000

	// *** As mentioned we need to use try-catch block while using ReentrantLock,
	// otherwise there could be a possibility of Deadlock situation
	public static void increment() {

		try {
			lock.lock();
			for (int i = 0; i < 10000; i++) {
				counter++;
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(counter);
	}

}
