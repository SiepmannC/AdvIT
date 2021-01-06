package AufgabenTeil1;


import Aufgabenteil1.Aufgabenteil1;
import Aufgabenteil1.Lok0;
import Aufgabenteil1.Lok1;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Aufgabenteil1Test {

    private long startTime = 0;
    private Thread t0;
    private Thread t1;
   private Aufgabenteil1 aufg;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("Tests werden gestartet");
        startTime = System.currentTimeMillis();
        ArrayList<Integer> bufferHistory = new ArrayList<>();
        //bufferHistory.add(0);
        aufg = new Aufgabenteil1(bufferHistory);

        t0 = new Thread(new Lok0(aufg));
        t1 = new Thread(new Lok1(aufg));

        t0.start();
        t1.start();

        while (true) {
            System.out.print("..");
            if (System.currentTimeMillis() - startTime > 60 * 1000) {
                System.out.println("SS");
                t0.interrupt();
                t1.interrupt();
                break;
            }
        }
    }

    @org.junit.jupiter.api.Test
    void main() {
        int counter = 0;
        for (int number : aufg.getBufferHistory()) {
            System.out.print(number + "->");
            assertEquals(number, counter % 2);
            counter++;
        }
        System.out.println("Anzahl der Durchfahrten" + aufg.getBufferHistory().size());
    }
}