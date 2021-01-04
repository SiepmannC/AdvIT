package Aufgabenteil1;

public class Lok0 implements Runnable{

	Aufgabenteil1 Info;
	
	
		public Lok0( Aufgabenteil1 Info) {
			this.Info = Info;
		}//Lok0
		
		
		public void run() {
		
		while(true) {
			
				//fahren
				Info.enterLok0();
				
				// Lok 0 im mittleren Teilstück
				Info.exitLok0();
	
		}//while
	}//run
}//class
