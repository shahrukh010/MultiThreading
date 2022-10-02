package thread.com.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Producer implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public Producer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {

		try {
			blockingQueue.put(4);
			blockingQueue.put(3);
			blockingQueue.put(1);
			Thread.sleep(1000);
			blockingQueue.put(2);
			blockingQueue.put(0);
			blockingQueue.put(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Consumer implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public Consumer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {

		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class BlockingQueueTest {

	public static void main(String... strings) {

		BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue();
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
