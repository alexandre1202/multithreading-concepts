package br.com.aab.threads.locks.synchronizedblock;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MySynchronizedMethods {
    private static final List<Integer> integers = new ArrayList<>();
    private static final Object lock = new Object();
    private static final Duration duration = Duration.ofMillis(500);
    private static final int LIMIT_HIGHER = 5;
    private static final int LIMIT_LOWER = 0;
    private int counter = 0;
    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (integers.size() == LIMIT_HIGHER) {
                    System.out.println("Production suspended. Waiting for consumers...");
                    lock.wait();
                } else {
                    integers.add(counter++);
                    System.out.println("Value [" + counter + "] in production...");
                    lock.notify();
                }
                Thread.sleep(duration);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (integers.size() == LIMIT_LOWER) {
                    System.out.println("Consuming suspended. Waiting for production...");
                    lock.wait();
                } else {
                    integers.remove(--counter);
                    System.out.println("Consuming the value [" + counter + "]");
                    lock.notify();
                }
                Thread.sleep(duration);
            }
        }
    }

    public static void main(String[] args) {
        MySynchronizedMethods processor = new MySynchronizedMethods();

        final Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
