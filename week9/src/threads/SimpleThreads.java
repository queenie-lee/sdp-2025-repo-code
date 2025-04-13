package threads;

import java.util.concurrent.TimeUnit;

public class SimpleThreads {

    public static void main(String[] args) {
        withoutDelay();
        threadSleep();
        threadSleepTimeUnit();
    }

    private static void withoutDelay() {
        System.out.println("Without delay");
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        runnable.run(); // runs in the main thread!

        Thread thread = new Thread(runnable); // creating a new thread
        thread.start(); // starting the thread!

        System.out.println("Done!");
    }

    private static void threadSleep() {
        System.out.println("With sleep");
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                Thread.sleep(1_000); // introduces delay. Use _ helps to group the digits for readability
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException ignore) { // sleep method throws InterruptedException - waiting for something to happen
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void threadSleepTimeUnit() {
        System.out.println("With sleep and TimeUnit");
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException ignore) {
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}