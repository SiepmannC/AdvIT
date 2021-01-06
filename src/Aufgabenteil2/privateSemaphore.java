package Aufgabenteil2;

import java.util.concurrent.Semaphore;

public class privateSemaphore extends Thread {

	private static Semaphore[] privSem = new Semaphore[2];
	

	private Semaphore mutex = new Semaphore(1);

	private boolean MitteFree = true;

	public privateSemaphore(Semaphore[] privSem) {
		this.privSem = privSem;
	}

	public void enterLok0() {
		try {

			privSem[0].acquire();
			System.out.println("Die Lok 0 fährt in das mittlere Teilstück");
			Thread.sleep((long) (Math.random() * 200));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void exitLok0() {
		try {
			mutex.acquire();
			MitteFree = true;
			System.out.println("							Die Lok 0 verlässt das Mittelstück");
			privSem[1].release();
			mutex.release();

			Thread.sleep((long) (Math.random() * 500));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void enterLok1() {

		try {

			privSem[1].acquire();
			System.out.println("	Die Lok 1 fährt in das mittlere Teilstück");
			Thread.sleep((long) (Math.random() * 200));

		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

	public void exitLok1() {
		try {
			mutex.acquire();
			MitteFree = true;
			System.out.println("							Die Lok 1 verlässt das Mittelstück");
			privSem[0].release();
			mutex.release();

			Thread.sleep((long) (Math.random() * 500));
		} catch (Exception e4) {
			e4.printStackTrace();
		}
	}

	public static void main(String[] args) {

		int Lokomotiven = 2;
		
			privSem[0]=new Semaphore(1,true);
			privSem[1]=new Semaphore(0,true);
		
		

		privateSemaphore[] Lok = new privateSemaphore[Lokomotiven];

		// Start von zwei Threads (Lok 0 & Lok 1)

		for (int i = 0; i < Lokomotiven; i++) {
			Lok[i] = new privateSemaphore(privSem);
		}

		new Thread(new Lok0_privSem(Lok[0])).start();
		new Thread(new Lok1_privSem(Lok[1])).start();

	}// main

}// class
