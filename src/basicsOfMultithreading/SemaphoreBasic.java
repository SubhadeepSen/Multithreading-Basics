package basicsOfMultithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// Thread Safe
enum Downloader {
	INSTANCE;

	// At most 3 Threads can access the resource, concurrently 3 threads can download
	private Semaphore semaphore = new Semaphore(3, true);

	public void download() {
		try {
			semaphore.acquire(); // acquiring the lock
			downloadData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release(); // releasing the lock
		}
	}

	private void downloadData() throws InterruptedException {
		System.out.println("Downloading data from web.......");
		Thread.sleep(1000);
	}
}

public class SemaphoreBasic {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 12; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					Downloader.INSTANCE.download();
				}
			});
		}
		executorService.shutdown();
	}

}
