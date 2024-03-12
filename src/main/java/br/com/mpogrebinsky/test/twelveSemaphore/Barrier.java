package br.com.mpogrebinsky.test.twelveSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Copyright (c) 2019-2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 *
 *  We initialize the semaphore to 0, to make sure every thread that tries to acquire the semaphore gets blocked.
 *  And the last thread to get to the barrier, releases the semaphore numberOfWorkers - 1
 *  since "numberOfWorkers - 1" threads are blocked on the semaphore.
 */
public class Barrier {
    private final int numberOfWorkers;
    private final Semaphore semaphore = new Semaphore(0); //** blank 1 **/
    private int counter = 0;  //** blank 2 **/
    private final Lock lock = new ReentrantLock();
 
    public Barrier(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }
 
    public void waitForOthers() throws InterruptedException {
        lock.lock();
        boolean isLastWorker = false;
        try {
            counter++;
 
            if (counter == numberOfWorkers) {
                isLastWorker = true;
            }
        } finally {
            lock.unlock();
        }
 
        if (isLastWorker) {
            semaphore.release(numberOfWorkers - 1);    //** blank 3 **/
        } else {
            semaphore.acquire();          
        }
    }
}
 
