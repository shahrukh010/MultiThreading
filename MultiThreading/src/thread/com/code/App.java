package thread.com.code;

public class App {

	private static int counter1 = 0;
	private static int counter2 = 0;

	/**
	 * By Default here is monitor lock uses
	 * The problem is that every object has single monitor lock.
	 * 
	 */
	//usually it is not good practice to use synchronized keyword in method declaration or  prototype.
	public static synchronized void increment1() {

		counter1++;
	}
	
	/**
	 * 
	 * notice one this in this case ,
	 * we have two method is which is synchronized as we know every object has single monitor lock
	 * so here only object are available(this)so suppose if increment1() is running then increment2() have to wait until completion of increment1(); 
	 *
	 * BECAUSE OF WE KNOW SINGLE MONITOR LOCK EVERY OBJECT HAS.
	 * 
	 * 
	 * 
	 * if we have two independent synchronized  method then the thread have to wait for each other to release the lock.
	 */

	//usually it is not good practice to use synchronized keyword in method declaration or  prototype.
	public static synchronized void increment2() {

		counter2++;
	}

	public void process() {

		Thread r1 = new Thread(new Runnable() {

			public void run() {

				for (int i = 0; i < 100; i++) {

					increment1();
				}
			}
		});

		Thread r2 = new Thread(new Runnable() {

			public void run() {

				for (int i = 0; i < 100; i++) {
					increment2();
				}
			}
		});

		r1.start();
		r2.start();
		try {

			r1.join();
			r2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task1: " + counter1);
		System.out.println("Task2: " + counter2);

	}

	public static void main(String... strings) {

		App app = new App();
		app.process();
	}
}
