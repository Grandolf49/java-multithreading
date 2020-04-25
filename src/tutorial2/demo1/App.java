package tutorial2.demo1;

import java.util.Scanner;

/**
 * We have 2 threads here. The main thread and a Processor thread. So we have 2
 * threads running and accessing the same variable "isRunning"
 * 
 * Processor Thread - reads variable "isRunning" Main Thread - calls shutDown()
 * function thereby writing the variable "isRunning"
 * 
 * Problem: When Java tries to optimize the code it is POSSIBLE (rarely) that it
 * sees the run() function is not really changing the value of "isRunning", it
 * won't bother checking the value of "isRunning" every time it enters the loop
 * and might just assume it to be true.
 * 
 * Solution: Declare the variable as volatile. It is used to prevent threads
 * from caching variables when they are not changed from within the thread
 * 
 * More details: https://www.geeksforgeeks.org/volatile-keyword-in-java/
 */
class Processor extends Thread {

	// private boolean isRunning = true;
	private volatile boolean isRunning = true;

	public void run() {
		while (isRunning) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void shutDown() {
		isRunning = false;
	}
}

public class App {

	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();

		System.out.println("Press return to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		proc1.shutDown();

		scanner.close();
	}

}
