package Aufgabenteil1;

public class Lok1 implements Runnable{
	Aufgabenteil1 Info;
	
	
		public Lok1( Aufgabenteil1 Info) {
			this.Info = Info;
		}
		
		
		public void run() {
		
		while(true) {
			try {
				
				//fahren
				Thread.sleep((long) Math.random()*1000);
				Info.enterLok1();
				// Mittelteil
				Thread.sleep((long) Math.random()*200);
				Info.exitLok1();
				
				
				
				
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
	}

}
