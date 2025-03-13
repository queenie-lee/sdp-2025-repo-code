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
        Thread vt = Thread.startVirtualThread(() ->
                System.out.println("TaskWithLambda started... as a virtual thread"));

        // thread pools
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
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
