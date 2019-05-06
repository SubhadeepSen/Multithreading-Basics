package basicsOfMultithreading;

public class ThreadWithRunnableInterface {

	public static void main(String[] args) {
		Thread t1 = new Thread(new RunnerRunnable1());
		Thread t2 = new Thread(new RunnerRunnable2());
		
		// Concurrent execution of Runner1 and Runner2
		t1.start();	// calls the overridden run() method of runnable implemented class which has been passed to the thread constructor 
		t2.start();
	}

}

class RunnerRunnable1 implements Runnable{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 1: " + i);
		}		
	}
}

class RunnerRunnable2 implements Runnable{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 2: " + i);
		}		
	}
}
