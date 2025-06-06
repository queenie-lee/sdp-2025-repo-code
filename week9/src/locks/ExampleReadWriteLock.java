package locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExampleReadWriteLock {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            Map<String, String> map = new HashMap<>();

            ReadWriteLock lock = new ReentrantReadWriteLock();

            executor.submit(() -> {
                lock.writeLock().lock();
                try {
                    Utils.sleep(1);
                    map.put("foo", "bar");
                }
                finally {
                    lock.writeLock().unlock();
                }
            });

            Runnable readTask = () -> {
                lock.readLock().lock(); // allows multiple threads to read, unless there is a write lock.
                try {
                    System.out.println(map.get("foo"));
                    Utils.sleep(1);
                }
                finally {
                    lock.readLock().unlock();
                }
            };
            executor.submit(readTask);
            executor.submit(readTask);
        }
    }
}
