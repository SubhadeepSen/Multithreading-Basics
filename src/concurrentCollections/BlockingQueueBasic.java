package concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueBasic {

	public static void main(String[] args) {
		ExecutorService ExecutorService = Executors.newCachedThreadPool();
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
		
		ExecutorService.submit(new FirstWorker(1, blockingQueue));
		ExecutorService.submit(new SecondWorker(2, blockingQueue));
		
		ExecutorService.shutdown();
	}

}

class FirstWorker implements Runnable {

	private int id;
	private BlockingQueue<Integer> blockingQueue;

	public FirstWorker(int id, BlockingQueue<Integer> blockingQueue) {
		this.id = id;
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		int counter = 1;
		System.out.println("Started thread with id " + id);
		while (true) {
			try {
				blockingQueue.put(counter);
				System.out.println("Putting item into the queue: " + counter);
				counter++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondWorker implements Runnable {

	private int id;
	private BlockingQueue<Integer> blockingQueue;

	public SecondWorker(int id, BlockingQueue<Integer> blockingQueue) {
		this.id = id;
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		int item = 0;
		System.out.println("Started thread with id " + id);
		while (true) {
			try {
				item = blockingQueue.take();
				System.out.println("Taking item from the queue: " + item);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
