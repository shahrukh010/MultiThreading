package thread.com.executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {

	/**
	 * 
	 * CountDownLatch is used to make sure that a task waits for other threads
	 * before it starts. To understand its application, let us consider a server
	 * where the main task can only start when all the required services have
	 * started.(example:install all dependencies related to main package then install main.) 
	 * 
	 * 
	 */

	private int id;
	private CountDownLatch countDownLatch;

	public Worker(int id, CountDownLatch latch) {
		this.id = id;
		this.countDownLatch = latch;
	}

	@Override
	public void run() {

		resolveDependencies();
		countDownLatch.countDown();
	}

	public void resolveDependencies() {

		try {
			System.out.println("Thread is: " + this.id + " installing dependencies...");
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class CountDownTest {

	public static void main(String... strings) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(7);// 7 thread after completion then only execute main task so make
														// sure what every countDownLatch(7)you are defining it should
														// be all execute then only main t ask operation will be
														// perform.

		for (int index = 0; index < 4; index++) {

			executorService.execute(new Worker(index + 1, latch));
		}
		try {
			// after 7 times countDown() call this await() i.e here it will wait until
			// countDown() method call 7 times because of we specified
			// CountDownLatch(7)times i.e here it will wait until countDown() method call 7
			// times because of we specified CountDownLatch(7)times.
			
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All the dependencies are installed.");
		executorService.shutdown();
	}
}
