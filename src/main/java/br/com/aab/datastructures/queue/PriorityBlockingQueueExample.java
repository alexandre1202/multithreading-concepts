package br.com.aab.datastructures.queue;

import java.util.concurrent.PriorityBlockingQueue;

class FirstPriorityWorker implements Runnable {
    private PriorityBlockingQueue<String> queue;

    public FirstPriorityWorker(PriorityBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("B");
            queue.put("H");
            queue.put("F");
            Thread.sleep(2000);
            queue.put("A");
            Thread.sleep(2000);
            queue.put("Z");
        } catch (InterruptedException e) {

        }

    }
}

class SecondPriorityWorker implements Runnable {
    private PriorityBlockingQueue<String> queue;

    public SecondPriorityWorker(PriorityBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
        } catch (InterruptedException e) {

        }

    }
}
public class PriorityBlockingQueueExample {
    /**
     * Its an implementation of the BlockingQueue interface
     *
     * - unbonded concurrent queue
     * - it uses the same ordering rules as the java.utilPriorityQueue class and we have to implement the
     *   Comparable interface to order the queue
     *  - The priority can be the same compare() == 0 case
     *  - no null items are allowed !!!
     */
}
