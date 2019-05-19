package concurrentCollections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierBasic {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {

			@Override
			public void run() {
				System.out.println("All tasks are completed....");
			}
		});

		for (int i = 1; i <= 5; i++) {
			executorService.submit(new CyclicBarrierWorker(i, cyclicBarrier));
		}

		executorService.shutdown();
	}

}

class CyclicBarrierWorker implements Runnable {
	private int id;
	private CyclicBarrier cyclicBarrier;
	private Random random;

	public CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
		this.id = id;
		this.cyclicBarrier = cyclicBarrier;
		this.random = new Random();
	}

	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread with id " + id + " started working...");
		int waitTime = random.nextInt(5000);
		try {
			Thread.sleep(random.nextInt(waitTime)); // sleeping for 0 to 5 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread with id " + id + " finished working after " + (waitTime / 1000) + " seconds");

		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}