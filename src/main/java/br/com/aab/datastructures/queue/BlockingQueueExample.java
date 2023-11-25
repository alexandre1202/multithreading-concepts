package br.com.aab.datastructures.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerWorker implements Runnable {
    private BlockingQueue<Integer> queue;

    public ProducerWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                System.out.println("Putting item into the counter = " + counter);
                queue.put(counter++);
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class ConsumerWorker implements Runnable {
    private BlockingQueue<Integer> queue;

    public ConsumerWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int counter = queue.take();
                System.out.println("Removing the counter [" + counter +"] from the queue size = " + queue.size());
                Thread.sleep(300);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
public class BlockingQueueExample {
    /**
     * Queue has a FIFO structure (first in first out)  but it is not a synchronized data structure !!!
     *
     * BlockingQueue -> an interface that represents a queue that is thread safe
     *      Put items or take items from it ...
     *
     *      For example: one thread putting items into the queue and another thread taking items from it ant the
     *                   same time !!!
     *                   We can do the producer-consumer pattern !!!
     *      put() putting items to the queue
     *      take() taking items from the queue
     */

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        ProducerWorker producerWorker = new ProducerWorker(queue);
        ConsumerWorker consumerWorker = new ConsumerWorker(queue);

        new Thread(producerWorker).start();
        new Thread(consumerWorker).start();
    }
}
