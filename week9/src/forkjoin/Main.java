package forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        FolderProcessor tmp = new FolderProcessor("/tmp", "java"); // search through various directories
        FolderProcessor usr = new FolderProcessor("/usr", "java");
        try (ForkJoinPool pool = new ForkJoinPool()) { // based on a bunch of platform threads. How many are dependent on the library itself (# of cores on machine). When subimt tasks using fork, get assigned to thread, threads start processing in parallel. **Work stealing** will steal from other threads if not being used, improving core efficiency by shrinking the longest queues, each thread will sit on its own queue.
            pool.execute(tmp);
            pool.execute(usr);

            while (pool.getActiveThreadCount() > 0) {
                // get some stats
                System.err.format("Number of processors: %s%n", pool.getParallelism());
                System.err.format("Thread count: %d%n", pool.getActiveThreadCount());
                System.err.format("Queued tasks: %d%n", pool.getQueuedTaskCount());
                System.err.format("Stole %d tasks%n", pool.getStealCount());

                System.err.println();
                Thread.sleep(5000);
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        results(tmp.join()); // get the results for the first one as an example
        results(usr.join());
    }

    static void results (List<String> results) {
        System.out.format("The number of matching entries is %d%n", results.size());
        results.forEach(s -> System.out.format("Matching: %s%n", s));
    }
}
