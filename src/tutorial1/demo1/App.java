package tutorial1.demo1;

class Runner extends Thread {

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class App {

	public static void main(String[] args) {
		Runner runner1 = new Runner();
		/**
		 * 1. New Thread Creation When a program calls the start() method, a new thread
		 * is created and then the run() method is executed. But if we directly call the
		 * run() method then no new thread will be created and run() method will be
		 * executed as a normal method call on the current calling thread itself and no
		 * multi-threading will take place.
		 * 
		 * 2. Multiple Invocation In Java’s multi-threading concept, another most
		 * important difference between start() and run() method is that we can’t call
		 * the start() method twice otherwise it will throw an IllegalStateException
		 * whereas run() method can be called multiple times as it is just a normal
		 * method calling. Let us understand it with an example:
		 */
		runner1.start();

		Runner runner2 = new Runner();
		runner2.start();
	}
}
