package Aufgabenteil2;



public class Lok0_privSem implements Runnable{
	
	

		privateSemaphore Infos;
		
		
			public Lok0_privSem( privateSemaphore Infos) {
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



