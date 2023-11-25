package br.com.aab.threads.locks.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleReentrantLock {
    private final static Lock lock = new ReentrantLock();
    private static int counter = 0;
    private void incrementCounter() {
        lock.lock();
        try {
            for (int i = 0; i < 10_000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleReentrantLock lockProcessor = new SimpleReentrantLock();
        Thread t1 = new Thread(() -> lockProcessor.incrementCounter());
        Thread t2 = new Thread(() -> lockProcessor.incrementCounter());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("counter = " + counter);
    }
}
