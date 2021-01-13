package POMA.Verification.BMC;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import POMA.Verification.BMCExamples.TCPExample.TCPChecker;

public class TestingThings {

	public static void main(String[] args) {
		int threads = Runtime.getRuntime().availableProcessors();
		 ExecutorService service = Executors.newFixedThreadPool(threads);
		// service.submit();
		for (int i = 1; i < 6; i++) {
			CVC4Thread threadguru1 = new CVC4Thread("Thread #" + i, i);
			threadguru1.start();
		}

//		Runnable thread = new Runnable() {
//			public void run() {
//				checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/TCPFiles/tcp");
//				try {
//					checker.check();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		
//		for (int i = 0; i < 36; i++) {
//			service.execute(thread);
//		}
//		service.shutdown();
	}

}

class CVC4Thread implements Runnable {
	volatile Thread cvc4thread;
	private String cvc4name;
	private int k;

	CVC4Thread(String name, int k) {
		cvc4name = name;
		this.k = k;
	}
	public void stop() {
		cvc4thread = null;
    }
	
	@Override
	public void run() {
		System.out.println("Thread running: " + cvc4name);
		TCPChecker checker = new TCPChecker(k);
		try {
			checker.check();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Thread has been interrupted");
		}

	}

	public void start() {
		System.out.println("Thread started: " + cvc4name);
		if (cvc4thread == null) {
			cvc4thread = new Thread(this, cvc4name);
			cvc4thread.start();
		}

	}
}