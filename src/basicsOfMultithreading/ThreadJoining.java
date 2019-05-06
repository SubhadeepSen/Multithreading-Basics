package basicsOfMultithreading;

public class ThreadJoining {

	public static void main(String[] args) {
		Thread t1 = new RunnerThreadJoin1();
		Thread t2 = new RunnerThreadJoin2();
		
		t1.start(); 
		t2.start();
		
		try {
			t1.join();	// this method waits for this thread to die (finish it's task)
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Thread Finished....");	// If we don't join, this completes first then other threads complete.
	}

}

class RunnerThreadJoin1 extends Thread{

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

class RunnerThreadJoin2 extends Thread{

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
