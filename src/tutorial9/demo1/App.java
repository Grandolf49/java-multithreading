package tutorial9.demo1;

/***
 * An example of Producer Consumer problem using low level synchronization i.e.
 * using synchronized blocks and locks.
 * 
 * @author chinm
 *
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		final Processor process = new Processor();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					process.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					process.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

}
