package concurrentCollections;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasic {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

		new Thread(new FirstConcurrentWorker(concurrentHashMap)).start();
		new Thread(new SecondConcurrentWorker(concurrentHashMap)).start();
	}

}

class FirstConcurrentWorker implements Runnable {
	private ConcurrentHashMap<String, Integer> concurrentHashMap;

	public FirstConcurrentWorker(ConcurrentHashMap<String, Integer> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
	}

	@Override
	public void run() {
		try {
			concurrentHashMap.put("D", 4);
			concurrentHashMap.put("A", 1);
			Thread.sleep(1000);
			concurrentHashMap.put("C", 3);
			Thread.sleep(1000);
			concurrentHashMap.put("B", 2);
			concurrentHashMap.put("E", 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SecondConcurrentWorker implements Runnable {
	private ConcurrentHashMap<String, Integer> concurrentHashMap;

	public SecondConcurrentWorker(ConcurrentHashMap<String, Integer> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(concurrentHashMap.get("C"));
			System.out.println(concurrentHashMap.get("E"));
			Thread.sleep(1000);
			System.out.println(concurrentHashMap.get("A"));
			System.out.println(concurrentHashMap.get("D"));
			Thread.sleep(1000);
			System.out.println(concurrentHashMap.get("B"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
