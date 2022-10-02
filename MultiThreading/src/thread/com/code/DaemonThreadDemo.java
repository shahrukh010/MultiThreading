package thread.com.code;

public class DaemonThreadDemo {

	class DaemonWorker implements Runnable {

		public void run() {

			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Daemon Thread running....");

			}
		}
	}

	class NormalWorker implements Runnable {

		public void run() {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Normal Thread finished...");
		}
	}
}
