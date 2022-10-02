package thread.com.code;

public class ThreadDemo {

	class Runner1 extends Thread {

		public void run() {

			for (int i = 0; i < 5; i++) {

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Runner1: " + i);

			}
		}
	}

	class Runner2 extends Thread {

		public void run() {

			for (int index = 0; index < 5; index++) {

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Runner2: " + index);
			}

		}
	}
}
