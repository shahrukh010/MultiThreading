package thread.com.code;

public class SynchronizedDemo extends Thread {

	private static int counter = 0;

	public synchronized void incrCounter() {

		counter++;
	}

	public void run() {

		for (int index = 0; index < 10000; index++) {
			incrCounter();
		}
//		System.out.println(counter);
	}

	public static void main(String[] args) {

		SynchronizedDemo sync = new SynchronizedDemo();
		Thread thread1 = new Thread(sync);
		Thread thread2 = new Thread(sync);

		thread1.start();
		thread2.start();
		try {
			thread2.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println(counter);

	}

}
