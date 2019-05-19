package concurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueBasic {
	public static void main(String[] args) {
		BlockingQueue<Person> blockingQueue = new PriorityBlockingQueue<>();
		
		new Thread(new FirstPriorityWorker(blockingQueue)).start();
		new Thread(new SecondPriorityWorker(blockingQueue)).start();
	}
}

class FirstPriorityWorker implements Runnable {
	private BlockingQueue<Person> blockingQueue;

	public FirstPriorityWorker(BlockingQueue<Person> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put(new Person("Jonh", 35));
			blockingQueue.put(new Person("Arial", 23));
			blockingQueue.put(new Person("Linda", 15));
			blockingQueue.put(new Person("Zoyee", 25));
			blockingQueue.put(new Person("Stephan", 45));
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SecondPriorityWorker implements Runnable {
	private BlockingQueue<Person> blockingQueue;

	public SecondPriorityWorker(BlockingQueue<Person> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		Person person = null;
		try {
			person = blockingQueue.take();
			System.out.println(person.getName() + " " + person.getAge());
			person = blockingQueue.take();
			System.out.println(person.getName() + " " + person.getAge());
			Thread.sleep(1000);
			person = blockingQueue.take();
			System.out.println(person.getName() + " " + person.getAge());
			person = blockingQueue.take();
			System.out.println(person.getName() + " " + person.getAge());
			Thread.sleep(1000);
			person = blockingQueue.take();
			System.out.println(person.getName() + " " + person.getAge());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Person implements Comparable<Person> {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person person) {
		return Integer.compare(age, person.getAge()); // Compare by age
		//return name.compareTo(person.getName()); //Compare by name
	}

}