package Aufgabenteil1;

public class Lok1 implements Runnable {
    private Aufgabenteil1 Info;

    public Lok1(Aufgabenteil1 Info) {
        this.Info = Info;
    }//Lok1

    public void run() {

        while (true) {

            //fahren
            Info.enterLok1();

            // Lok 1 im mittleren Teilstück
            Info.exitLok1();

        }//while
    }//run
}//class

