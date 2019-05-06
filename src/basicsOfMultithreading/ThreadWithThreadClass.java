package basicsOfMultithreading;

public class ThreadWithThreadClass {

	public static void main(String[] args) {
		Thread t1 = new RunnerThread1();
		Thread t2 = new RunnerThread2();
		
		// Concurrent execution of Runner1 and Runner2
		t1.start();	// calls the overridden run() method of Thread class 
		t2.start();
	}

}

class RunnerThread1 extends Thread{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 1: " + i);
		}		
	}
}

class RunnerThread2 extends Thread{

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 2: " + i);
		}		
	}
}
