package thread.com.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;

	public FirstWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {

		try {

			blockingQueue.put("B");
			blockingQueue.put("D");
			blockingQueue.put("E");
			Thread.sleep(1000);
			blockingQueue.put("A");
			blockingQueue.put("C");
			Thread.sleep(1000);
			blockingQueue.put("F");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SecondWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;

	public SecondWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class BlockingQueueDemo {

	public static void main(String... strings) {

		BlockingQueue<String> blockingQueue = new PriorityBlockingQueue();
		FirstWorker first = new FirstWorker(blockingQueue);
		SecondWorker second = new SecondWorker(blockingQueue);

		new Thread(first).start();
		new Thread(second).start();

	}
}
