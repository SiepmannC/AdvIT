package Aufgabenteil1;

public class Lok0 implements Runnable{

	Aufgabenteil1 Info;
	
	
		public Lok0( Aufgabenteil1 Info) {
			this.Info = Info;
		}
		
		
		public void run() {
		
		while(true) {
			try {
				//fahren
				Thread.sleep((long) Math.random()*1000);
				Info.enterLok0();
				// Mittelteil
				Thread.sleep((long) Math.random()*200);
				Info.exitLok0();
				
				
				
				
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
	}



}
