package thread.com.code;

import java.util.ArrayList;
import java.util.List;

class ProcessThread {

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 5;
	private static final List<Integer> alist = new ArrayList<>();
	private static Object lock1 = new Object();
	private static int value = 0;

	public void producer() throws InterruptedException {

		synchronized (lock1) {
			while (true) {

				if (alist.size() == MAX_VALUE) {

					System.out.println("waiting for removing item...");
					value = 0;
					lock1.wait();
				} else {
					System.out.println("Adding value: " + value);
					alist.add(value);
					value++;
					lock1.notify();//notify the other thread which are in waiting stage in this case consumer thread are.
								   //here,other thread are notified when current thread goes into waiting stage
					Thread.sleep(500);
				}
			}
		}
	}

	public void consumer() throws InterruptedException {

		synchronized (lock1) {
			while (true) {

				if (alist.size() == MIN_VALUE) {

					System.out.println("waiting for adding items..");
					lock1.wait();//put the current thread into waiting stage , and we already define notify() method to notify the waiting thread to restart their operation in producer().
				} else {
					System.out.println("removing value: " + alist.remove(alist.size() - 1));
					lock1.notify();
					//do further operation (notify() method not immediately  notify to other thread. in this case you can see while loop is executing ..
					Thread.sleep(500);
				}
			}

		}
	}

}

public class WaitNotifyDemo {

	ProcessThread process = new ProcessThread();

	public void process() {

		Thread thread1 = new Thread(new Runnable() {

			public void run() {

				try {
					process.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			public void run() {

				try {

					process.consumer();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		});

		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String... strings) {

		WaitNotifyDemo wnd = new WaitNotifyDemo();
		wnd.process();
	}

}
