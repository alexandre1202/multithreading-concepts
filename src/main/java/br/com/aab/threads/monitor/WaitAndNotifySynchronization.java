package br.com.aab.threads.monitor;

import java.time.Duration;

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the producer...");
            wait();   //  it means that the producer will wait for the execution of the consumer
            System.out.println("Again in the producer...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(1));
        synchronized (this) {
            System.out.println("Consume method is executed...");
            notify();    // it means that the producer can continue with the execution
        }
    }
}
public class WaitAndNotifySynchronization {
    public static void main(String[] args) {
        Process process = new Process();
        Thread consume = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread produce = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        consume.start();
        produce.start();
    }
}
