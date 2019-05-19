package basicsOfMultithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceBasic {

	public static void main(String[] args) {
		/*1*/
		ExecutorService executorService = Executors.newCachedThreadPool();

		// creating 5 jobs and assigning to 5 threads and 5 threads can run in parallel
		for (int i = 0; i < 5; i++) {
			executorService.submit(new WorkerExecutor());
		}
		
		/*2*/
		/*ExecutorService executorService = Executors.newFixedThreadPool(3);

		// creating 5 jobs but there are only 3 threads, so 3 threads can run in parallel
		//	hence first 3 threads will take first 3 jobs, once job completed rest 2 threads will take 2 jobs
		for (int i = 0; i < 5; i++) {
			executorService.submit(new WorkerExecutor());
		}*/
		
		/*3*/
		/*ExecutorService executorService = Executors.newSingleThreadExecutor();

		// creating 5 jobs but there is only 1 thread. So only 1 thread can execute at a time, no concurrent execution
		for (int i = 0; i < 5; i++) {
			executorService.submit(new WorkerExecutor());
		}*/
		
		executorService.shutdown();
	}

}

class WorkerExecutor implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}