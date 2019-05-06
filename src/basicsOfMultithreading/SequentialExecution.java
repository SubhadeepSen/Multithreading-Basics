package basicsOfMultithreading;

public class SequentialExecution {

	public static void main(String[] args) {
		Runner1 runner1 = new Runner1();
		Runner2 runner2 = new Runner2();
		
		runner1.startRunner();	// first completes this execution
		runner2.startRunner();	// then completes this execution
	}

}

class Runner1 {
	public void startRunner() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 1: " + i);
		}
	}
}

class Runner2 {
	public void startRunner() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Runner 2: " + i);
		}
	}
}
