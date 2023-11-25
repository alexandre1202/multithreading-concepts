package br.com.aab.datastructures.copyingdata;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int sharedData = 0;

    public int readData() {
        lock.readLock().lock(); // Acquire the read lock
        try {
            // Perform read operations on sharedData
            return sharedData;
        } finally {
            lock.readLock().unlock(); // Release the read lock
        }
    }

    public void writeData(int newValue) {
        lock.writeLock().lock(); // Acquire the write lock
        try {
            // Perform write operation on sharedData
            sharedData = newValue;
        } finally {
            lock.writeLock().unlock(); // Release the write lock
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();

        // Multiple threads can read simultaneously
        Runnable readTask = () -> {
            int data = example.readData();
            System.out.println("Read data: " + data);
        };

        // Only one thread can write at a time
        Runnable writeTask = () -> {
            int newValue = (int) (Math.random() * 100);
            example.writeData(newValue);
            System.out.println("Write data: " + newValue);
        };

        Thread writerThread = new Thread(writeTask);
        Thread readerThread1 = new Thread(readTask);
        Thread readerThread2 = new Thread(readTask);

        writerThread.start();
        readerThread1.start();
        readerThread2.start();
    }
}
