package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SyncProblems {
    private static int count = 0;

    public static void main(String[] args) {
        for (int x = 0; x < 10; x++)
            addNumbersInParallel();
    }

    static void addNumbersInParallel() {
        count = 0;
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) { // only 2 tasks will run concurrently
            IntStream.range(0, 10_000) // start 10,000 tasks
                    .forEach(i -> executor.submit(SyncProblems::increment));
        }
        System.out.println(count);
    }

    static void increment() {
        count++;
    } // accessing shared memory causes race condition
/*
    Each object has a monitor
    Entering into a synchronised method picks up the lock,
    Exiting releases it
    One at a time.

    Entering into a synchronised block, the thread will always read the most recent value of the variable.
    BUT potentially expensive.
    To terminate a thread, better to use Volatile
* */
    static synchronized void incrementSync() {
        // the synchronized modifier is equivalent to the following
        // synchronized (SyncProblems.class) { // gets lock on the entire class
        // because this is a class method
            count++;
        // }
    }
/*
    private int m;

    synchronized void objectInc(){
        m++;
    }

    // equivalent to:

    void objectInc() {
        synchronized (this) { // gets lock on the object
            m++;
        }
    }

 */
}
