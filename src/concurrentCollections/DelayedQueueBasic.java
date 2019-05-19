package concurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueBasic {

	public static void main(String[] args) {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

		try {
			queue.put(new DelayedWorker(1000, "This is first message"));
			queue.put(new DelayedWorker(10000, "This is sencond message"));
			queue.put(new DelayedWorker(5000, "This is third message"));
			queue.put(new DelayedWorker(3000, "This is fourth message"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (!queue.isEmpty()) {
			try {
				String message = queue.take().getMessage();
				System.out.println("Taking message: " + message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class DelayedWorker implements Delayed {
	private long duration;
	private String message;

	public DelayedWorker(long duration, String message) {
		this.duration = System.currentTimeMillis() + duration;
		this.message = message;
	}

	@Override
	public int compareTo(Delayed otherDelay) { // used to sort the elements based on delay
		DelayedWorker otherWorker = (DelayedWorker) otherDelay;
		if (this.duration < otherWorker.getDuration()) {
			return -1;
		} else if (this.duration > otherWorker.getDuration()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public long getDelay(TimeUnit timeUnit) { // returns the delay
		return timeUnit.convert(this.duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}