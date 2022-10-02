package thread.com.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FixedThreadPool implements Runnable {

	private Integer id = 0;

	public FixedThreadPool(int id) {
		this.id = id;

	}

	public void run() {

		System.out.println("Task is :" + this.id + " for task process id is:" + Thread.currentThread().getId());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ExecutorThreadPool {

	public static void main(String... strings) {

		// we can create number of thread in this case we have 2 thread which is execute
		// simultaneously..
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int index = 0; index < 6; index++) {

			executorService.execute(new FixedThreadPool(index + 1));
		}
		executorService.shutdown();
	}

}
