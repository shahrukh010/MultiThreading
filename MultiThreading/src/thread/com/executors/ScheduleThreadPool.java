package thread.com.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class StockUpdate implements Runnable {

	public void run() {

		System.out.println("updateing and downloading data from web.....");
	}

}

public class ScheduleThreadPool {

	public static void main(String... strings) {

		ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(1);
		//Every 5000 i.e every 5 second update and download result.
		scheduleExecutor.scheduleAtFixedRate(new StockUpdate() , 1000, 5000, TimeUnit.MILLISECONDS);


	}
}
