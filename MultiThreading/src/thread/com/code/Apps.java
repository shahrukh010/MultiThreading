package thread.com.code;

class Process {

	public void producer() throws InterruptedException {

		synchronized (this) {
			System.out.println("Running the producer method...");
			wait();// wait method released the intrinsic lock
			System.out.println("Again producer method is running...");
		}
	}

	public void consumer() throws InterruptedException {

		Thread.sleep(5000);
		synchronized (this) {
			System.out.println("Consumer method is executed...");
			notify();// here it is not going to notify wait() method you can do further more
			for (int i = 0; i < 5; i++) {
				System.out.println("index: " + i);
				Thread.sleep(200);
			}
		}

	}
}

public class Apps {

	public void process() {

		Process process = new Process();

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				try {
					process.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				try {
					process.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	}

	public static void main(String... strings) {

		Apps apps = new Apps();
		apps.process();
	}
}
