package br.com.aab.threads.locks.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a hypothetical scenario where we have a shared resource represented by a bounded buffer.
 * Multiple producer threads add items to the buffer, and multiple consumer threads remove items from the buffer.
 * However, we want to ensure that producers wait if the buffer is full, and consumers wait if the buffer is empty.
 */
public class BoundedBuffer<T> {
    private final Queue<T> buffer;
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    public void produce(T item) throws InterruptedException {
        lock.lock();
        try {
            // Wait if buffer is full
            while (buffer.size() == capacity) {
                notFull.await();
            }
            // Produce item and signal waiting consumers
            buffer.add(item);
            System.out.println(Thread.currentThread().getName() + " produced: " + item);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T consume() throws InterruptedException {
        lock.lock();
        try {
            // Wait if buffer is empty
            while (buffer.isEmpty()) {
                notEmpty.await();
            }
            // Consume item and signal waiting producers
            T item = buffer.poll();
            System.out.println(Thread.currentThread().getName() + " consumed: " + item);
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);

        // Create producer and consumer threads
        for (int i = 0; i < 3; i++) {
            Thread producerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        buffer.produce(j);
                        Thread.sleep(100); // Simulate producing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Producer-" + i);

            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        buffer.consume();
                        Thread.sleep(200); // Simulate consuming time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Consumer-" + i);

            // Start producer and consumer threads
            producerThread.start();
            consumerThread.start();
        }
    }
}
