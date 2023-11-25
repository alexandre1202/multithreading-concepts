package br.com.aab.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private int id;

    public Task(int id) {
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
            throw new RuntimeException(e);
        }
    }
}
public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i <5; i++) {
            executorService.execute(new TaskShutdown(i));
        }
    }

}
