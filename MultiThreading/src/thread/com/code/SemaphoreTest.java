package thread.com.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {

	//Best if requirement is singleton pattern then please use enum
	//Because of it is thread saf.
	INSTANCE;

	private static Semaphore semaphore = new Semaphore(5, true);//5->at a time  5 thread download five record.

	public void downloadData() {

		try {
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private void download() {

		System.out.println("Downloading Data From Web....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class SemaphoreTest {

	public static void main(String... strings) {

		//create a several thread....
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int index = 0; index < 12 ; index++) {
			
			executorService.execute(new Runnable(){
				
				public void run() {
					
					Downloader.INSTANCE.downloadData();//here you can see at a time 5  thread download five record because of our Semaphore contain 5 thread limit.
					
				}
			});
		}
	}
}
