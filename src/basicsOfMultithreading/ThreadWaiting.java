package basicsOfMultithreading;

public class ThreadWaiting {

	public static void main(String[] args) {
		Thread t1 = new RunnerThreadWait1();
		Thread t2 = new RunnerThreadWait2();
		
		// Concurrent execution of Runner1 and Runner2
		t1.start();	// calls the overridden run() method of Thread class 
		t2.start();
	}

}

class RunnerThreadWait1 extends Thread{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 1: " + i);
			try {
				Thread.sleep(300);	// waiting for 300 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}

class RunnerThreadWait2 extends Thread{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 2: " + i);
			try {
				Thread.sleep(300);	// waiting for 300 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
