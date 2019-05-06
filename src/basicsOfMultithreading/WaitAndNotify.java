package basicsOfMultithreading;

public class WaitAndNotify {

	public static void main(String[] args) {
		Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
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

}

class Processor {

	public void producer() throws InterruptedException {
		synchronized (this) {
			System.out.println("Got the Class Intrinsic Lock: I'm producer");
			//wait(); // waiting for other thread (t2) to complete (infinite time)
			wait(5000);	// waits only for maximum 5 seconds
			System.out.println("Producer again after waiting for the other thread");
		}
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(1000); // will get the lock after 1 sec
		synchronized (this) {
			System.out.println("Got the Class Intrinsic Lock: I'm consumer");
			notify(); // notifying thread (t1) to continue it's execution by acquiring the lock
			//notifyAll(); // notify all the threads waiting for the lock (on the same object)
		}
	}
}
