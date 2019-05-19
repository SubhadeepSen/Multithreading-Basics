package concurrentCollections;

import java.util.concurrent.Exchanger;

public class ExchangerBasic {

	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();

		new Thread(new FirstThread(exchanger)).start();
		new Thread(new SecondThread(exchanger)).start();

	}
}

class FirstThread implements Runnable {
	private int counter;
	private Exchanger<Integer> exchanger;

	public FirstThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter = counter + 1;
			System.out.println("First thread increments counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondThread implements Runnable {
	private int counter;
	private Exchanger<Integer> exchanger;

	public SecondThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter = counter - 1;
			System.out.println("Second thread decrements counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}