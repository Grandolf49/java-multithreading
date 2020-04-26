package tutorial5.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	public void run() {
		System.out.println("Starting: " + id);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed: " + id);
	}

}

/**
 * Problem: A program that creates a new thread for every request would spend
 * more time and consume more system resources in creating and destroying
 * threads than processing actual requests. Since active threads consume system
 * resources, a JVM creating too many threads at the same time can cause the
 * system to run out of memory.
 * 
 * Solution: Limit the number of threads being created.
 * 
 * Thread Pool: It uses a previously created threads to execute current tasks
 * and solves the problem of thread cycle overhead and resource thrashing.
 * 
 * Source: https://www.geeksforgeeks.org/thread-pools-java/
 */
public class App {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
		}

		/**
		 * This will wait for all the tasks to get completed before shutdown
		 */
		executor.shutdown();

		System.out.println("All tasks submitted.");

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All tasks completed.");
	}

}
