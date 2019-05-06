package basicsOfMultithreading;

public class Synchronization {

	private static int counter = 0;

	// only one thread can execute this method at a time.
	public static synchronized void incrementCounter() {
		counter++;
	}

	public static void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					incrementCounter();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					incrementCounter();
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

	public static void main(String[] args) {
		process();
		// Here counter will always prints 200 because of synchronization
		System.out.println(counter);
	}

}
