package thread.com.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {

	private Integer id = 0;

	public Task(int id) {
		this.id = id;
	}

	@Override
	public void run() {

		try {
			System.out.println("The Task Id:" + id + "  Process Id: " + Thread.currentThread().getId());
			long duration = (long) Math.random() * 5;
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}
}

public class ExecutorFramework {

	public static void main(String... strings) {

		// it is single thread that will execute the task sequentially.
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int index = 0; index < 5; index++) {

			executorService.execute(new Task(index));
		}
		// we have to manually shutdown executor otherwise it will not stop.
		executorService.shutdown();
	}

}
