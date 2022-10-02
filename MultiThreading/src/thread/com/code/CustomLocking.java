package thread.com.code;

/**
 * 
 * @author hector custom lock:By default monitor lock or intrinsic lock uses by
 *         thread that's why parallel execution happening if one class contain
 *         multiple thread then each thread have to have completion of each
 *         other thread because of intrinsic lock uses object level locking and
 *         by default class contain only one object itself above problem we are
 *         solving using custom lock In custom lock we will create new object
 *         for each thread.
 *
 */
public class CustomLocking {

	private static int counter1 = 0;
	private static int counter2 = 0;
	private static final Object lock1 = new Object();
	private static final Object lock2 = new Object();

	public static void increment1() {

		// each thread have their own lock so execution will independently
		synchronized (lock1) {
			counter1++;
		}
	}

	public static void increment2() {

		// each thread have their own lock so execution will independently
		synchronized (lock2) {
			counter2++;
		}
	}

	public static void process() {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				for (int index = 0; index < 100; index++) {
					increment1();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				for (int index = 0; index < 100; index++) {

					increment2();
				}
			}
		});

		t1.start();
		t2.start();
		try {

			t1.join();
			t2.join();
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}
		System.out.println("Task1: " + counter1);
		System.out.println("Task2: " + counter2);
	}

}
