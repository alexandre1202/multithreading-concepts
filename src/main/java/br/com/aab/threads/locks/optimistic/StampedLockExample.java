package br.com.aab.threads.locks.optimistic;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    /**
     * StampedLock is a relatively advanced concurrency utility introduced in Java 8, providing support for
     *      optimistic locking. Here's a common tech interview question related to StampedLock and its answer:
     *
     * Interview Question:
     *      Explain how StampedLock works and provide an example of its usage.
     *
     * Answer:
     *      StampedLock is a new type of lock introduced in Java 8 that allows for three modes of locking: reading, writing, and optimistic reading. It is designed to provide better performance than traditional ReadWriteLock in certain scenarios.
     *
     * Here's a basic explanation of the modes:
     *
     * Reading Lock (ReadLock): Allows multiple threads to read the protected resource concurrently.
     * Writing Lock (WriteLock): Exclusive lock for writing, no other thread can hold any lock (read or write)
     *      when a thread holds the write lock.
     * Optimistic Reading (tryOptimisticRead()): A non-blocking mode that doesn't prevent other threads from
     *      acquiring the write lock. The thread performs operations under the assumption that the values
     *      read will not be changed by another thread.
     */

    private double x, y;
    private final StampedLock lock = new StampedLock();

    // Method demonstrating write lock
    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // Method demonstrating read lock
    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // Other methods using tryConvertToWriteLock, tryConvertToReadLock, etc. for more advanced scenarios.

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();

        // Usage example
        example.move(3.0, 4.0);
        double distance = example.distanceFromOrigin();
        System.out.println("Distance from origin: " + distance);
    }
}
