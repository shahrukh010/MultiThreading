package thread.com.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Processor {

	protected static int counter = 0;
	// Lock is interface
	private static final Lock lock = new ReentrantLock();

	public void increment() {

		lock.lock();// it is same like synchronized keyword if thread1 is acquire lock then thread2
					// have to wait
		try {
			for (int i = 0; i < 10000; i++)
				counter++;
		} finally {
			lock.unlock();
		}
	}
}

public class ReentrantLockDemo {

	public static void main(String... strings) {

		Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				processor.increment();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				processor.increment();
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
		System.out.println("The counter: " + processor.counter);
	}
}
