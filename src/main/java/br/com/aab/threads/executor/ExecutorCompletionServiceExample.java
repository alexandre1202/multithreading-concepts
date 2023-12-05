package br.com.aab.threads.executor;

import java.util.concurrent.*;

public class ExecutorCompletionServiceExample {
    public static void main(String[] args) {
        // Create an Executor (e.g., ThreadPoolExecutor)
        Executor executor = Executors.newFixedThreadPool(5);

        // Create an ExecutorCompletionService
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        // Submit tasks to the ExecutorCompletionService
        for (int i = 1; i <= 5; i++) {
            final int taskNumber = i;
            completionService.submit(() -> {
                // Simulate a task that takes some time to execute
                Thread.sleep(1000);
                return taskNumber;
            });
        }

        // Retrieve and process results as they become available
        for (int i = 0; i < 5; i++) {
            try {
                Future<Integer> completedTask = completionService.take();
                int result = completedTask.get();
                System.out.println("Result of Task " + result + " received.");
                // Process the result...
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Shutdown the executor
        ((ExecutorService) executor).shutdown();
    }
}
