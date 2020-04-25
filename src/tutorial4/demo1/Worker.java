package tutorial4.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting ...");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		/**
		 * After executing both the threads, we often get ArrayOutOfBoundsExecptions.
		 * These problems occur when accessing shared data from multiple threads. Since
		 * there are multiple operations involved while writing to a list, only one
		 * thread must perform them.
		 * 
		 * Problem: After using synchronized keyword, the execution time almost doubles.
		 * This happens because there is only one intrinsic lock for Worker object, if a
		 * thread is executing stageOne() method, the other thread cannot even execute
		 * stageTwo() method in spite of being independent methods.
		 * 
		 * Solution: Create separate locks for each method and use synchronized blocks
		 * instead of synchronized methods. The processing time has again been reduced
		 * to the original time.
		 * 
		 * We can also use lists themselves as locks but it is a good practice to use
		 * separate locks for separate methods.
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time taken: " + (end - start));
		System.out.println("List 1: " + list1.size());
		System.out.println("List 2: " + list2.size());

	}
}
