package br.com.aab.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskShutdown implements Runnable {
    private int id;

    public TaskShutdown(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        long duration = Double.valueOf(Math.random() * 5).longValue();
        System.out.println("Task id " + this.id
                + " - Thread : " + Thread.currentThread().getName()
                + " duration of " + duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class ShutdownSingleThreadExecutorExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i <5; i++)
            executor.execute(new TaskShutdown(i));

        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
