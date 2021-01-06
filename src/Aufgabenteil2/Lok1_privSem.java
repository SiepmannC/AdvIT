package Aufgabenteil2;

public class Lok1_privSem implements Runnable {
	
	privateSemaphore Infos;
	
	
	public Lok1_privSem( privateSemaphore Infos) {
		this.Infos = Infos;
	}//Lok0
	
	
	public void run() {
	
	while(true) {
		
			//fahren
			Infos.enterLok1();
			
			// Lok 0 im mittleren Teilstück
			Infos.exitLok1();

	}//while
}//run
}//class


