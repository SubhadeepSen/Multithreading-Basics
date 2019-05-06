package basicsOfMultithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerReentrantLock {

	public static void main(String[] args) {
		ReentrantWorker worker = new ReentrantWorker();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	}

}

class ReentrantWorker {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();	// Need to create this for wait and notify operations

	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Got the Reentrant Lock: I'm producer");
		condition.await(5000, TimeUnit.MILLISECONDS); // waits only for maximum 5 seconds
		System.out.println("Producer again after waiting for the other thread");
		lock.unlock();	// unlocking is must
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Got the Reentrant Lock: I'm consumer");
		condition.signal(); // notifying thread (t1) to continue it's execution by acquiring the lock
		lock.unlock();	// unlocking is must
	}
}
