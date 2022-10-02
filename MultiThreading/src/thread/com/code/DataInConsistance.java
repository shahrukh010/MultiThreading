package thread.com.code;

public class DataInConsistance {

	private int count = 0;

	// this method executed by single thread at a time.
	/**
	 * synchronized:->when we use synchronized keyword then this method will be access by only one thread at a given time.
	 * because of monitor lock by default monitor lock won't allowed more then one thread to execute.
	 *By Default sychronized uses interinsic lock.
	 */
	private synchronized void increment() {

		count++;
	}

	public void process() {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				for (int index = 0; index < 100; index++) {
					increment();

				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				for (int index = 0; index < 100; index++) {
					increment();
				}
			}
		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("Total counter: " + this.count);
	}

}
