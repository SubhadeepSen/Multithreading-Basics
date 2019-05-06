package basicsOfMultithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();

		// creating 5 jobs
		for (int i = 1; i <= 5; i++) {
			Future<String> future = executorService.submit(new WorkerCallable(i));
			list.add(future);
		}

		// printing the response received from each thread
		for (Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();
	}

}

class WorkerCallable implements Callable<String> {

	private int id;

	public WorkerCallable(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "You Job ID: " + id; // returning response
	}

}
