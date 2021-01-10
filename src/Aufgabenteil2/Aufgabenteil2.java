package Aufgabenteil2;


import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Aufgabenteil2 extends Thread {

	// Initialisieren der Semaphore
	public static Semaphore[] privSem = new Semaphore[2];
	ArrayList<Integer> history;

	public ArrayList<Integer> getHistory() {
		return history;
	}

	public Aufgabenteil2() {
		// Leerer Constructor

	}// Aufgabenteil2

	public Aufgabenteil2(ArrayList<Integer> history, Semaphore[] privSem) {
		this.history = history;
		this.privSem = privSem;
	}

	public void enterLok0() {
		try {
			// privates Semaphor
			privSem[0].acquire();

			System.out.println("Die Lok 0 fährt in das mittlere Teilstück");
			history.add(0);
			// Fahrt durch das mittlere Stück
			Thread.sleep((long) (Math.random() * 200));
		} catch (Exception e1) {
			e1.printStackTrace();
		} // try/catch

	}// enterLok0

	public void exitLok0() {
		try {

			System.out.println("							Die Lok 0 verlässt das Mittelstück");

			// Freigeben der anderen Lokomotive
			privSem[1].release();
            history.add(1);
			// Lokomotive fährt die restliche Strecke
			Thread.sleep((long) (Math.random() * 500));
		} catch (Exception e2) {
			e2.printStackTrace();
		} // try/catch
	}// exitLok0

	public void enterLok1() {

		try {

			// privates Semaphor
			privSem[1].acquire();
			System.out.println("	Die Lok 1 fährt in das mittlere Teilstück");

			// Lok0 fährt durch das Mittelstück.
			Thread.sleep((long) (Math.random() * 200));

		} catch (Exception e3) {
			e3.printStackTrace();
		} // try/catch
	}// enterLok1

	public void exitLok1() {
		try {

			System.out.println("							Die Lok 1 verlässt das Mittelstück");

			// Freigeben der anderen Lokomotive
			privSem[0].release();

			// Lokomotive fährt die restliche Strecke
			Thread.sleep((long) (Math.random() * 500));
		} catch (Exception e4) {
			e4.printStackTrace();
		} // try/catch
	}// exitLok1

	public static void main(String[] args) {
		

		privSem[0] = new Semaphore(1, true);
		privSem[1] = new Semaphore(0, true);

		Aufgabenteil2[] Lok = new Aufgabenteil2[2];

		
		//Initialisieren von zwei Privaten Semaphoren
		for (int i = 0; i < 2; i++) {
			Lok[i] = new Aufgabenteil2();
		}
		// Start von zwei Threads (Lok 0 & Lok 1)
		new Thread(new Lok0_privSem(Lok[0])).start();
		new Thread(new Lok1_privSem(Lok[1])).start();

	}// main

}// class