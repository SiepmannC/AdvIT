package Aufgabenteil2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.*;

class privateSemaphoreTest {

    private static Semaphore[] privSem = new Semaphore[2];
    private Aufgabenteil2[] Lok;
    private static ArrayList<Integer> history0 = new ArrayList<>();
    private static ArrayList<Integer> history1 = new ArrayList<>();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("Tests werden gestartet");
        long startTime = System.currentTimeMillis();
        privSem[0] = new Semaphore(1, true);
        privSem[1] = new Semaphore(0, true);

        Lok = new Aufgabenteil2[2];
        Lok[0] = new Aufgabenteil2(history0, privSem);
        Lok[1] = new Aufgabenteil2(history1, privSem);

        Thread t0 = new Thread(new Lok0_privSem(Lok[0]));
        Thread t1 = new Thread(new Lok1_privSem(Lok[1]));

        t0.start();
        t1.start();

        while (true) {
            System.out.print(".");
            if (System.currentTimeMillis() - startTime > 30 * 1000) {// Zeitlimit kann hier verändert werden
                System.out.println("~");
                try {
                    t0.interrupt();
                    t1.interrupt();
                } catch (Exception e) {
                    System.out.println("Durchlauf beendet");
                }
                break;
            }
        }
    }

    @org.junit.jupiter.api.Test
    void main() {
        history0 = Lok[0].getHistory();
        System.out.println("Anzahl der Durchfahrten" + history0.size());


        int counter = 0;
        for (int number : history0) {
            System.out.print(number + "->");
            assertEquals(number, counter % 2);
            counter++;
        }
    }

    @org.junit.jupiter.api.Test
    void erstenBeiden() {
        history0 = Lok[0].getHistory();
        assertEquals(history0.get(0), 0);
        assertEquals(history0.get(1), 1);
    }

}
