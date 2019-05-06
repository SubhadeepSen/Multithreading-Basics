package basicsOfMultithreading;

/*
 * When you are having multiple synchronized methods or blocks, it is not advisable to use Class Intrinsic Lock on the same class.
 */

public class SynchronizedBlock {

	private static int count1 = 0;
	private static int count2 = 0;

	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void incrementCount1() {
		// Using the lock on different instance or class. This resolves the problem
		synchronized (lock1) {
			count1++;
		}
	}

	public static void incrementCount2() {
		// Using the lock on different instance or class. This resolves the problem
		synchronized (lock2) {
			count2++;
		}
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
