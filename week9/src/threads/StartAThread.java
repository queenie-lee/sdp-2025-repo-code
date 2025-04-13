package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartAThread {

    public static void main(String[] args) {
        // platform threads
        Thread task = new Task();
        task.start();

        Thread taskWithRunnable = new Thread(new TaskWithRunnable());
        taskWithRunnable.start();

        Thread taskWithLambda = new Thread(() ->
                System.out.println("TaskWithLambda started..."));
        taskWithLambda.start();

        // virtual thread (requires Java 21)
        // startVirtualThread = factory method
        Thread vt = Thread.startVirtualThread(() ->
                System.out.println("TaskWithLambda started... as a virtual thread"));

        // thread pools
        // newFixedThreadPool = factory method
        // try with resources block. ExecutorService extends autoclosable. At the end, the resource is closed. Resources will terminate after exiting this block.
        // Check 40:00 to review try with resources block.
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) { // creates a pool of 10 platform threads
            executorService.execute(() ->
                    System.out.println("TaskWithLambda started... from executor"));
        }
    }
}

class Task extends Thread {

    @Override
    public void run() {
        System.out.println("threads.Task started...");
    }
}

class TaskWithRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("threads.TaskWithRunnable started...");
    }
}
