package tutorial6.demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	public void run() {
		System.out.println("Started.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		latch.countDown();
	}

}

public class App {

	/**
	 * CountDownLatch is used to make sure that a task waits for other threads
	 * before it starts.
	 */
	public static void main(String[] args) {

		/**
		 * Specifying the number of threads the countdown latch should wait for.
		 */
		CountDownLatch latch = new CountDownLatch(3);

		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}

		/**
		 * Waits until the count down latch has counted down to zero.
		 */
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed.");
	}

}
