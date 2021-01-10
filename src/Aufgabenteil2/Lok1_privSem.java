package Aufgabenteil2;

public class Lok1_privSem implements Runnable {
    private Aufgabenteil2 Infos;

    Lok1_privSem(Aufgabenteil2 Infos) {
        this.Infos = Infos;
    }//Lok0

    public void run() {

        while (true) {

            //fahren
            Infos.enterLok1();

            // Lok 0 im mittleren Teilstück
            Infos.exitLok1();

        }//while
    }//run
}//class


