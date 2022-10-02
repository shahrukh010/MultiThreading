package thread.com.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class App implements Callable<String> {

	private int id = 0;

	public App(int id) {

		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "id: " + id;
	}

}

public class CallableTest {

	public static void main(String... strings) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		List<Future<String>> list = new ArrayList<>();

		for (int index = 0; index < 5; index++) {

			Future<String> future = executorService.submit(new App(index));
			list.add(future);
		}

		for (Future<String> future : list) {
			System.out.println(future.get());

		}
	}
}
