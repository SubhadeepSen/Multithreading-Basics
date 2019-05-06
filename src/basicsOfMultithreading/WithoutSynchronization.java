package basicsOfMultithreading;

public class WithoutSynchronization {

	private static int counter = 0;

	public static void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					counter++;
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					counter++;
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
		/*
		 * Here counter will not always be 200 because when thread1 is reading the value
		 * of counter it may not be updated the by thread2 and the same is true for
		 * thread2. To solve this issue synchronization is required so that only one
		 * thread reads and updates the value of counter.
		 */

		System.out.println(counter);
	}

}
