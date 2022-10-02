package thread.com.code;

public class Main {

	public static void main(String... strings) {

//		ThreadDemo threademo = new ThreadDemo();
//		ThreadDemo.Runner1 runner1 = threademo.new Runner1();
//		Thread t1 = new Thread(runner1);
//
//		ThreadDemo.Runner2 runner2 = threademo.new Runner2();
//		Thread t2 = new Thread(runner2);
//
//		t1.start();
//		t2.start();
//		try {
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//
//		}
//
//		System.out.println("All Thread are Finished.");

//****************************************************************************************************	
//****************************************************************************************************	

		// Example of Daemon Thread
		System.out.println();
		System.out.println();
//		System.out.println("........Example of Daemon Thread........");

		/*
		DaemonThreadDemo daemonThreadDemo = new DaemonThreadDemo();
		DaemonThreadDemo.DaemonWorker worker = daemonThreadDemo.new DaemonWorker();
		DaemonThreadDemo.NormalWorker normal = daemonThreadDemo.new NormalWorker();
		Thread workerthread = new Thread(worker);
		Thread normalthread = new Thread(normal);
		workerthread.setDaemon(true);
		workerthread.start();
		normalthread.start();// here you can see if normal thread are finished then worker thread
								// automatically destroy you can see DaemonWorker class it contain infinite loop
								// but once normal thread is finished automatically destroy the DaemonWorker
								// thread because of we make DaemonWorker thread as a daemon thread. for more
								// details refer Picture/multithreading directory material

*/
		
		
	DataInConsistance data = new DataInConsistance();
	data.process();
		
		
		
	}
}
