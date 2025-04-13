package threads;

public class KillingAThread {

    public static void main(String[] args) throws InterruptedException {
        //usingFlag();
        //usingInterrupt();
    }

    static void usingFlag() {

        StopWithFlagTask task1 = new StopWithFlagTask();
        StopWithFlagTask task2 = new StopWithFlagTask();

        task1.start();
        task2.start();

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }

        task1.stop();
        task2.stop();
    }

    static void usingInterrupt() {
        StopWithInterruptTask task1 = new StopWithInterruptTask();
        StopWithInterruptTask task2 = new StopWithInterruptTask();

        task1.start();
        task2.start();

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }

        task1.interrupt();
        task2.interrupt();
    }
}

class StopWithFlagTask implements Runnable {

    // Volatile: lightweight version of synchronised. Used for termination. It guarantees that each time this variable is read, we get the last value.
    // Guarantees that every thread will get the latest value of the variable.
    private volatile boolean flag = false;

    public void start() {
        Thread worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        flag = true;
    }

    @Override
    public void run() {
        while (!flag) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " Running...");
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " Stopped");
    }
}

class StopWithInterruptTask implements Runnable {
    private Thread worker;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void interrupt() {
        worker.interrupt();
    }

    @Override
    public void run() { // TODO: review 1:10
        while (!Thread.currentThread().isInterrupted()) { // checking if it was interrupted.
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " Running...");
            }
            catch (InterruptedException e) { // relying on java API: interrupt
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted with reason: " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " Stopped");
    }
}

