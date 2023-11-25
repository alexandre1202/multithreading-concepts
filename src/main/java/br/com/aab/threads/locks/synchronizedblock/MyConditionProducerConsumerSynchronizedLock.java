package br.com.aab.threads.locks.synchronizedblock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Integer counter = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) counter++;
            System.out.println("Producing data...");
            condition.await();
            System.out.println("Back to producer...");
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            for (int i = counter; i >= 0; i--) counter--;
            System.out.println("Consuming data...");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
public class MyConditionProducerConsumerSynchronizedLock {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread tProducer = new Thread(() -> {
            try {
                worker.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tConsumer = new Thread(() -> {
            try {
                worker.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        tProducer.start();
        tConsumer.start();

        tProducer.join();
        tConsumer.join();

    }
}
