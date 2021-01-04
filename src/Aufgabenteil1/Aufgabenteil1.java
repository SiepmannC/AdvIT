package Aufgabenteil1;

import java.util.concurrent.Semaphore;


public class Aufgabenteil1 extends Thread {
	
	Semaphore mutex = new Semaphore(1);
	Semaphore full = new Semaphore(1);
	Semaphore empty = new Semaphore(0);
	int Buffer = 0;
	

	
	public Aufgabenteil1 () {
		
	}
	
	public void enterLok0() {
		
		
		//überprüfen ob Puffer aktuell gleich 0
		try{
			
		
		full.acquire();
		mutex.acquire();
			System.out.println("Die Lok 0 fährt in das mittlere Teilstück");
			
			Thread.sleep((long) (Math.random()*400));
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
	}
	public void exitLok0() {
		
		//
		Buffer += 1;
		System.out.println("							Die Lok 0 verlässt das Mittelstück");
		
		mutex.release();
		empty.release();
		
		
		
		
	}
	
public void enterLok1() {
		
		//überprüfen ob Puffer aktuell gleich 0
	try {
		
	
		
		empty.acquire();
	mutex.acquire();
		System.out.println("	Die Lok 1 fährt in das mittlere Teilstück");
		
		
			Thread.sleep((long) (Math.random()*200));
			
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
		
	
	public void exitLok1() {
		
		
		//
		Buffer -= 1;
		System.out.println("							Die Lok 1 verlässt das Mittelstück");
		mutex.release();
		full.release();
		
		
	}
	
	public static void main(String[] args) {
		
		Aufgabenteil1 Aufg = new Aufgabenteil1();
		
		new Thread(new Lok0(Aufg)).start();
		new Thread(new Lok1(Aufg)).start();
		
		
		
	}
	
	}


	