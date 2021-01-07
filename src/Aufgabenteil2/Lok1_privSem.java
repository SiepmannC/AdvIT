package Aufgabenteil2;

public class Lok1_privSem implements Runnable {
	
	Aufgabenteil2 Infos;
	
	
	public Lok1_privSem( Aufgabenteil2 Infos) {
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


