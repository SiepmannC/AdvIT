package Aufgabenteil1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Aufgabenteil1 extends Thread {
	
	/*  Unsere Überlegungen für die Bearbeitung des Erzeuger/Verbraucher Problems:
		Unsere Überlegung war, dass jeweils eine Lokomotive als Erzeuger und eine als Verbraucher agiert. 
		Der mittlere Schienenabschnitt stellt dabei den Puffer dar. Der Puffer hat eine Größe von eins, 
		weil die Lokomotiven immer abwechselnd fahren sollen. Die Lok 0 fährt als erstes los und ist somit 
		der Erzeuger. Sie fährt durch den mittleren Schienenabschnitt und “erzeugt” am Ende eine Einheit. 
		Diese Einheit wird dann von der Lok 1, unserem Verbraucher, verwendet und ist am Ende des mittleren 
		Abschnittes verbraucht worden. Wenn der Puffer = 1 ist, also voll, dann wartet der Erzeuger, bis der 
		Puffer wieder Platz frei hat, also gleich 0 ist. Wenn der Puffer = 0, dann kann der Verbraucher 
		nichts aus dem Puffer entnehmen und wartet bis wieder etwas in den Puffer geladen wurde. 
	 */


    //Initialisieren der Semaphore und der Puffer Variable
    private Semaphore mutex = new Semaphore(1);
    private Semaphore full = new Semaphore(1);
    private Semaphore empty = new Semaphore(0);
    private int Buffer = 0;

    public ArrayList<Integer> getBufferHistory() {
        return bufferHistory;
    }

    private ArrayList<Integer> bufferHistory;


    public Aufgabenteil1(ArrayList<Integer> bufferHistory) {
        this.bufferHistory = bufferHistory;

    }
    //Aufgabenteil1

    void enterLok0() {
        try {
            //Überprüfung ob Puffer gefüllt werden kann
            full.acquire();

            //Start des Kritischen Abschnittes
            mutex.acquire();
            System.out.println("Die Lok 0 fährt in das mittlere Teilstück");

            //Lok0 fährt durch das Mittelstück
            Thread.sleep((long) (Math.random() * 200));

        } catch (Exception e) {
            e.printStackTrace();
        }//try/catch


    }//enterLok0

    void exitLok0() {

        //Erzeugen einer Einheit/ Erhöhen des Buffers
        bufferHistory.add(Buffer);
        Buffer += 1;
        System.out.println("							Die Lok 0 verlässt das Mittelstück");
		
		/*beenden der Kritschen Abschnittes, gestartet in der Methode enterLok0.
		Dies wird erst hier beendet, weil der gesamte mittlere Schienenabschnitt der kritische Abschnitt ist*/
        mutex.release();
		
		/*empty.release() um dem Verbraucher zu zeigen, dass der Buffer gefüllt ist und es nun möglich ist, 
		den Inhalt des Buffers zu verbrauchen */
        empty.release();

        //Lokomotive fährt die restliche Strecke
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (Exception e) {
            e.printStackTrace();
        }//try/catch


    }//exitLok0

    void enterLok1() {


        try {


            //Überprüfung ob Puffer geleert werden kann
            empty.acquire();

            //Start des Kritischen Abschnittes  (Lok 1 in dem mittleren Teilstück)
            mutex.acquire();
            //Für die einfachere Überprüfung der Ergebnisse ist dieser Teil ein Stück eingerückt
            System.out.println("	Die Lok 1 fährt in das mittlere Teilstück");

            //Lok0 fährt durch das Mittelstück.
            Thread.sleep((long) (Math.random() * 200));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }//try/catch
    }//enterLok1


    void exitLok1() {


        //Verbrauch einer Einheit/ Senkung des Buffers
        bufferHistory.add(Buffer);
        Buffer -= 1;
        System.out.println("							Die Lok 1 verlässt das Mittelstück");
		
		/*beenden der Kritschen Abschnittes, gestartet in der Methode enterLok1.
		Dies wird erst hier beendet, weil der gesamte mittlere Schienenabschnitt den kritische Abschnitt darstellt */
        mutex.release();
		
		/*full.release() um dem Erzeuger zu zeigen, dass der Buffer leer ist und es nun möglich ist, 
		den Inhalt des Buffers erneut zu erzeugen */
        full.release();

        //Lokomotive fährt die restliche Strecke
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (Exception e) {
            e.printStackTrace();
        }//try/catch


    }//exitLok1

    public static void main(String[] args) {

        //Start von zwei Threads (Lok 0 & Lok 1)
        Aufgabenteil1 Aufg = new Aufgabenteil1(new ArrayList<>());

        new Thread(new Lok0(Aufg)).start();
        new Thread(new Lok1(Aufg)).start();


    }//main

}//class


	