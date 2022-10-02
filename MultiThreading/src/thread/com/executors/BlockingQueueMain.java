package thread.com.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable<Person> {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.age = age;
		this.name = name;
	}

	public Person() {
	}

	public int getAge() {
		return this.age;
	}

	public String getName() {
		return this.name;
	}

	class Worker implements Runnable {

		private BlockingQueue<Person> blockingQueue;

		public Worker(BlockingQueue<Person> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		public void run() {

			try {
				blockingQueue.put(new Person("Annie", 25));
				blockingQueue.put(new Person("Bridget", 28));
				Thread.sleep(1000);
				blockingQueue.put(new Person("Hector", 27));
				blockingQueue.put(new Person("Nic", 29));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	class Consumer implements Runnable {

		private BlockingQueue<Person> blockingQueue;

		public Consumer(BlockingQueue<Person> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		public void run() {
			try {

				Thread.sleep(5000);
				System.out.println(blockingQueue.take());
				System.out.println(blockingQueue.take());
				Thread.sleep(1000);
				System.out.println(blockingQueue.take());
				Thread.sleep(1000);
				System.out.println(blockingQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public int compareTo(Person otherPerson) {
		return this.name.compareTo(otherPerson.getName());
	}

	public String toString() {

		return this.name + "," + this.age;
	}
}

public class BlockingQueueMain {

	public static void main(String... strings) {

		Person person = new Person();
		BlockingQueue<Person> blockingQueue = new PriorityBlockingQueue();

		Person.Worker worker = person.new Worker(blockingQueue);
		Person.Consumer consumer = person.new Consumer(blockingQueue);

		new Thread(worker).start();
		;
		new Thread(consumer).start();

	}

}
