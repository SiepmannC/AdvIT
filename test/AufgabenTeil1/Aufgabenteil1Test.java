package AufgabenTeil1;


import Aufgabenteil1.Aufgabenteil1;
import Aufgabenteil1.Lok0;
import Aufgabenteil1.Lok1;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Aufgabenteil1Test {

    private Aufgabenteil1 aufg;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("Tests werden gestartet");
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> bufferHistory = new ArrayList<>();
        //bufferHistory.add(0);
        aufg = new Aufgabenteil1(bufferHistory);

        Thread t0 = new Thread(new Lok0(aufg));
        Thread t1 = new Thread(new Lok1(aufg));

        t0.start();
        t1.start();

        while (true) {
            System.out.print("..");
            if (System.currentTimeMillis() - startTime > 30 * 1000) {// Zeitlimit kann hier verändert werden
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
        ArrayList<Integer> history = aufg.getBufferHistory();
        for (int number : history) {
            System.out.print(number + "->");
            assertEquals(number, counter % 2);
            counter++;
        }
        System.out.println(" ");
        System.out.println("Anzahl der Durchfahrten" + aufg.getBufferHistory().size());
    }

    @org.junit.jupiter.api.Test
    void erstenBeiden() {
        assertEquals(aufg.getBufferHistory().get(0), 0);
        assertEquals(aufg.getBufferHistory().get(1), 1);
    }
}