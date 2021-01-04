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
		
		
		//�berpr�fen ob Puffer aktuell gleich 0
		try{
			
		
		full.acquire();
		mutex.acquire();
			System.out.println("Die Lok 0 f�hrt in das mittlere Teilst�ck");
			
			Thread.sleep((long) (Math.random()*400));
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
	}
	public void exitLok0() {
		
		//
		Buffer += 1;
		System.out.println("							Die Lok 0 verl�sst das Mittelst�ck");
		
		mutex.release();
		empty.release();
		
		
		
		
	}
	
public void enterLok1() {
		
		//�berpr�fen ob Puffer aktuell gleich 0
	try {
		
	
		
		empty.acquire();
	mutex.acquire();
		System.out.println("	Die Lok 1 f�hrt in das mittlere Teilst�ck");
		
		
			Thread.sleep((long) (Math.random()*200));
			
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
		
	
	public void exitLok1() {
		
		
		//
		Buffer -= 1;
		System.out.println("							Die Lok 1 verl�sst das Mittelst�ck");
		mutex.release();
		full.release();
		
		
	}
	
	public static void main(String[] args) {
		
		Aufgabenteil1 Aufg = new Aufgabenteil1();
		
		new Thread(new Lok0(Aufg)).start();
		new Thread(new Lok1(Aufg)).start();
		
		
		
	}
	
	}


	