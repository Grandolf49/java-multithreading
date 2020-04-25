package tutorial3.demo1;

public class App {

	private int count = 0;

	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});

		t1.start();
		t2.start();

		/**
		 * The main thread will wait until both the threads finish executing. This is
		 * achieved by calling the join() function on the threads
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Problem: Ideally until this point both the threads have finished executing.
		 * Thus the value of count should be 20000. This might be true in some cases but
		 * sometimes it is possible that the value of count is drastically different
		 * than 20000.
		 * 
		 * This happens due to multiple threads accessing the same variable and also
		 * reading and writing. This leads to data inconsistency.
		 * 
		 * Solution: Use synchronized keyword. Every object in Java has an intrinsic
		 * lock or a monitor lock or a mutex. To call any synchronized method of a
		 * class, we have to first acquire the lock. Moreover, on a same object only one
		 * thread can acquire the intrinsic lock at a time. The other threads just wait
		 * until they can acquire the lock.
		 * 
		 * synchronized keyword also guarantees that the state of the shared variable is
		 * made availabel to all the threads trying to access it. Thus we don't need to
		 * declare them volatile
		 */
		System.out.println("Count is: " + count);
	}
}
