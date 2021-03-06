package concurrentCollections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchBasic {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(5);
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 1; i <= 5; i++) {
			executorService.submit(new LatchWorker(i, countDownLatch));
		}

		try {
			countDownLatch.await(); // Waiting for other threads to complete their tasks
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All prerequisites are done....");
		executorService.shutdown();

	}

}

class LatchWorker implements Runnable {
	private int id;
	private CountDownLatch countDownLatch;

	public LatchWorker(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		countDownLatch.countDown();
	}

	private void doWork() {
		System.out.println("Thread with id " + id + " started working...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread with id " + id + " finished working...");
	}

}