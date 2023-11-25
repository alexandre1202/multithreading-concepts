package br.com.aab.threads.monitor;

public class MonitorOfIntrinsicLock {
    public static int counter = 0;
    public static synchronized void increment() {
        for (int i = 0; i < 10; i++) {
            counter++;
        }
    }
    public static void process() {
        Thread t1 = new Thread(() -> increment());
        Thread t2 = new Thread(() -> increment());
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        process();
        System.out.println("counter = " + counter);
    }
}
