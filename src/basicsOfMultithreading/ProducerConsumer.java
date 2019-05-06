package basicsOfMultithreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

	public static void main(String[] args) {
		NumberProducerConsumer numberProducerConsumer = new NumberProducerConsumer();

		// This thread calls the producer() method when gets the lock
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					numberProducerConsumer.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Producer-Thread");

		// This thread calls the consumer() method when gets the lock
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					numberProducerConsumer.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Consumer-Thread");

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

class NumberProducerConsumer {

	private Object lock = new Object();
	private List<Integer> numbers = new ArrayList<>();
	private static final int LOWER_THRESHOLD = 0;
	private static final int UPPER_THRESHOLD = 5;
	private int value = 0;

	public void producer() throws InterruptedException {
		// Adding elements to the list until it reaches to the threshold
		// Once the threshold reached it releases the lock
		synchronized (lock) {
			while (true) {
				if (numbers.size() == UPPER_THRESHOLD) {
					System.out.println("Waiting for removing items....");
					lock.wait(); // releasing the lock
				} else {
					numbers.add(value);
					System.out.println(Thread.currentThread().getName() + " Adding: " + value);
					value++;
					lock.notify(); // informing other threads to acquire the lock
				}
				Thread.sleep(500);
			}
		}
	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (numbers.size() == LOWER_THRESHOLD) {
					System.out.println("Waiting for adding items....");
					lock.wait(); // releasing the lock
				} else {
					System.out.println(Thread.currentThread().getName() + " Removing: " + numbers.remove(--value));
					lock.notify(); // informing other threads to acquire the lock
				}
				Thread.sleep(500);
			}
		}
	}
}