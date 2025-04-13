package threads;

public class IllegalMonitorStateExceptionDemo {

    //This object is used for synchronization among threads.
    private final static Object obj = new Object();

    public static void main(String[] args) {
        WaitingThread waitingThread = new WaitingThread();
        NotifyingThread notifyingThread = new NotifyingThread();

        waitingThread.start();
        notifyingThread.start();
    }

    public static class WaitingThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " starts waiting");
                synchronized (obj) { // calling method wait can only be done within a synchronised block.
                    //Calling wait() method outside of synchronised area
                    obj.wait();    // throws IllegalMonitorStateException
                } // exiting the block: wait releases lock and puts the thread on waiting list
                System.out.println(Thread.currentThread().getName() + " ends waiting");
            }
            catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " gets interrupted");
            }
        }
    }

    public static class NotifyingThread extends Thread {

        @Override
        public void run() {
            try {
                // Thread sleep for 5 sec
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " notifies");
                synchronized (obj) { // calling notify can only be done within a synchronised block
                    // Calling notify() outside of synchronized area
                    obj.notifyAll();         // throws IllegalMonitorStateException
                } // exiting the block: notify picks up the first thread from the waiting list. NotifyAll picks up all threads from waiting list.
            }
            catch (InterruptedException ex) {
                System.err.println(Thread.currentThread().getName() + " gets interrupted");
            }
        }
    }
}
