package Aufgabenteil2;



public class Lok0_privSem implements Runnable{
	
	

	Aufgabenteil2 Infos;
		
		
			public Lok0_privSem( Aufgabenteil2 Infos) {
				this.Infos = Infos;
			}//Lok0
			
			
			public void run() {
			
			while(true) {
				
					//fahren
					Infos.enterLok0();
					
					// Lok 0 im mittleren Teilstück
					Infos.exitLok0();
		
			}//while
		}//run
	}//class



