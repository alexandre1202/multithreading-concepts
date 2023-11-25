package br.com.aab.threads.synchronizers.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Latch -> a single thread can wait for onther threads
 * Cyclic Barrier -> multiple threads can wit for each other
 *
 * A cyclicBarrier is used in situations where you want to create a group of tasks to perform work
 * in a concurrent manner + wait until they are all finished before moving on the the next step
 * -> something like join()
 * -> something lik CountDownLatch
 *
 * CountDownLatch; -> one-shot event
 * Cyclic Barrier: it can be reused over and over again
 *      + cyclic Barrier has a barrier action: a runnable, that will run automatically
 *        when the count reach 0 !!
 *
 * new CyclicBarrier(N) -> N threads will wait for each other
 *      WE CAN NOT REUSE LATCHES LIKE WE CAN WITH CyclicBarriers -> reset()
 */

class Worker implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.random = new Random();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        try {
            System.out.println("Thead with ID " + this.id
                    + " starts working on Thread Id " + Thread.currentThread().threadId());
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All Threads has been executed ...");
    }
}
public class CyclicBarrierExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("All tasks have been finished ..."));

        for (int i = 0; i < 5; i++)
            service.execute(new Worker(i, cyclicBarrier));

        service.shutdown();
    }
}
