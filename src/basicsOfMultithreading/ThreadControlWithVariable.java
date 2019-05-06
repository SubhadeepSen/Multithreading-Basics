package basicsOfMultithreading;

public class ThreadControlWithVariable {

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.start();

		try {
			Thread.sleep(3000); // main thread is sleeping for 3 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		worker.setTerminated(true); // Terminating worker thread
		System.out.println("Main thread finished after 3 seconds...");
	}

}

class Worker extends Thread {
	// private boolean isTerminated = false; // Initializing to false, may be stored in Cache or in RAM
	private volatile boolean isTerminated = false; // Initializing to false, will definitely be stored in RAM only, not in cache

	@Override
	public void run() {
		while (!isTerminated) { // When Thread is not terminated
			System.out.println("Worker thread working...");
			try {
				sleep(300); // Sleeping for 300 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

}