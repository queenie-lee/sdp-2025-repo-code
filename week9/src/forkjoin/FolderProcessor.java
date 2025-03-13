package forkjoin;

/* Example of the Fork/Join Framework which is based upon the work of Doug Lea */

/* This example searches for any files with a matching extension within
 * the current folder or its sub-folders.
 *
 * The code makes use of ForkJoinPool instead of ExecutorService as FJP
 * uses a work-stealing algorithm to balance the workload.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

class FolderProcessor extends RecursiveTask<List<String>> {
    // cannot use record structure as it already extends Record
    private final String path;
    private final String extension;

    FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        System.out.format("Task: %s%n", Thread.currentThread().getName());

        List<String> list = new ArrayList<>(); // files stored in folder
        List<FolderProcessor> tasks = new ArrayList<>(); // list of tasks to perform

        var file = new File(path); // The contents of the current folder
        File[] content = file.listFiles(); // files in the folder
        if (content != null) { // some files/folders to process
            //System.err.format("Looking at %s%n", content[i]);
            Arrays.stream(content).forEach(item -> {
                if (item.isDirectory()) {
                    System.out.format("Examining directory %s%n", item);
                    // create a task to examine the folder/directory
                    var task = new FolderProcessor(item.getAbsolutePath(), extension);
                    task.fork(); // run the task
                    tasks.add(task); // add to the list of tasks
                }
                else { // an ordinary file
                    if (check(item.getName())) {
                        list.add(item.getAbsolutePath());
                    }
                }
            });
        }
        return Stream.concat(list.stream(),
                        tasks.stream()
                                .map(ForkJoinTask::join)
                                .flatMap(Collection::stream))
                .toList();
    }

    boolean check(String fileName) {
        //System.out.format("Examining %s%n", fileName);
        return fileName.endsWith(extension);
    }

}