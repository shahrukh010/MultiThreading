package thread.com.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {

	private static Lock lock = new ReentrantLock();
	private static java.util.concurrent.locks.Condition condition = lock.newCondition();

	public void producer() throws InterruptedException {

		lock.lock();
		System.out.println("Producer method...");
		Thread.sleep(5000);
		condition.await();
		System.out.println("Again producer...");
		lock.unlock();
	}

	public void consumer() throws InterruptedException {

		lock.lock();
		System.out.println("Consumer method...");
		Thread.sleep(5000);
		condition.signal();
		lock.unlock();
	}
}

public class Condition {

	private static Worker worker = new Worker();

	public static void process() {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				try {
					worker.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static void main(String... strings) {

		Condition.process();

	}
}
