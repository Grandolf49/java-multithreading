package tutorial8.demo1;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {

		/**
		 * Synchronize on Processor object. Hence, this keyword is used. This block will
		 * only run when it has acquired the intrinsic lock of Processor object.
		 */
		synchronized (this) {
			System.out.println("Producer thread running...");

			/**
			 * wait() – this forces the current thread to wait until some other thread
			 * invokes notify() or notifyAll() on the same object. The synchronized block
			 * will loose control of the intrinsic lock thereby allowing other threads to
			 * acquire the lock.
			 * 
			 * wait() can only be called within synchronized code blocks.
			 */
			wait();

			/**
			 * To resume wait(), other thread that's locked on the same object should call a
			 * method notify()
			 */
			System.out.println("Resumed.");
		}
	}

	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);

		/**
		 * Synchronize on the same Processor object to use the same intrinsic lock for
		 * both the threads.
		 */
		synchronized (this) {
			System.out.println("Waiting for return key...");
			scanner.nextLine();
			System.out.println("Return key pressed!");

			/**
			 * notify() will notify other thread which has locked on the same object that if
			 * it is waiting, it can wake up and start executing. The choice of exactly
			 * which thread to wake is non-deterministic and depends upon the
			 * implementation.
			 * 
			 * notify() can only be called within a synchronize block.
			 * 
			 * VERY IMPORTANT POINT: notify() does not release the lock. The lock will only
			 * be released after the complete block is executed.
			 */
			notify();
		}
	}

}
