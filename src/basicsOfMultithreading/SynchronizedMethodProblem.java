package basicsOfMultithreading;

public class SynchronizedMethodProblem {

	private static int count1 = 0;
	private static int count2 = 0;

	/*
	 * When synchronized keyword is used on a method, no other threads can access
	 * the methods (other synchronized methods) inside the class as it uses class
	 * intrinsic lock. Here if Thread1 is incrementing count1 then thread2 can
	 * increment count2, but it will not happens because of the class intrinsic
	 * lock. Thread2 will have to wait unless Thread1 completes it's execution. This
	 * problem can be resolved using synchronized blocks.
	 */
	public static synchronized void incrementCount1() {
		count1++;
	}

	public static synchronized void incrementCount2() {
		count2++;
	}

	public static void increment() {
		for (int i = 1; i <= 100; i++) {
			incrementCount1();
			incrementCount2();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment(); // incrementing count1 and count2 using Thread1. count1=count2=100
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment(); // incrementing count1 and count2 again using Thread2. count1=count2=200
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

		System.out.println("Count1: " + count1 + "  Count2: " + count2);
	}

}
