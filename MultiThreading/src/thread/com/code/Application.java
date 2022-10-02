package thread.com.code;

public class Application {

	private static double counter1 = 0.0;
	private static double counter2 = 0.0;

	public static void increment1() {

		// class level lock
		// if method was not static then use object level lock(this)
		synchronized (Application.class) {
			counter1++;
		}
	}

	//By Default in java interinsic lock are there
	//which is not good because of for single thread it acquire class level lock or current object(current object acquired by single thread after completion current thread then other thread get chance to get current object.
	//until completion first synchronized method it won't allowed to executed second thread to other synchronized method or block
	public synchronized static void increment2() {

		counter2++;
	}

	public static void process() {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				for (int i = 0; i < 100; i++) {
					increment1();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				for (int i = 0; i < 100; i++) {
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
			e.printStackTrace();
		}
		System.out.println("Task1:"+counter1);
		System.out.println("Task2:"+counter2);
	}

	public static void main(String... strings) {

		Application.process();
	}

}
