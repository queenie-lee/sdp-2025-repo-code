package executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CancelATask {
    public static void main(String[] args) {
        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(1)) {

            ScheduledFuture<?> future = executor
                    .schedule(() -> System.out.println("Some Task"), 5, TimeUnit.SECONDS); // schedule gives us a future object

            System.out.println("Before Cancel - Task is done : " + future.isDone());
            System.out.println("Before Cancel - Task is cancelled : " + future.isCancelled());

            if (!future.isDone()) { // gets status of a task
                future.cancel(false); // task's flag is set to false so it must finish
            }

            System.out.println("Task is done : " + future.isDone());
            System.out.println("Task is cancelled : " + future.isCancelled());
        }
    }
}