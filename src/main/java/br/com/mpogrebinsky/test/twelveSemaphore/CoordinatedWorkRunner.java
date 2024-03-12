package br.com.mpogrebinsky.test.twelveSemaphore;

/**
 * Copyright (c) 2019-2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */
public class CoordinatedWorkRunner implements Runnable {
    private final Barrier barrier;
 
    public CoordinatedWorkRunner(Barrier barrier) {
        this.barrier = barrier;
    }
 
    @Override
    public void run() {
        try {
            task();
        } catch (InterruptedException e) {
        }
    }
 
    private void task() throws InterruptedException {
        // Performing Part 1
        System.out.println(Thread.currentThread().getName() 
                + " part 1 of the work is finished");
 
        barrier.waitForOthers();
 
        // Performing Part2
        System.out.println(Thread.currentThread().getName() 
                + " part 2 of the work is finished");
    }
}
