package command;

import java.util.ArrayList;
import java.util.List;

public class CommandLambda {

    public static void log(String message) {
        System.out.println("Logging: " + message);
    }

    public static void save(String message) {
        System.out.println("Saving: " + message);
    }

    public static void send(String message) {
        System.out.println("Sending: " + message);
    }

    public static void execute(List<Runnable> tasks) {
        tasks.forEach(Runnable::run);
    }

    // No need to create own classes, anonymous classes are used here.
    public static void main(String... args) {
        List<Runnable> tasks = new ArrayList<>(); // Runnable has a single parameter (run)
        tasks.add(() -> log("Hi"));
        tasks.add(() -> save("Cheers"));
        tasks.add(() -> send("Bye"));

        execute(tasks);
    }
}
