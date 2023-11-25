package br.com.aab.threads.timeslicing.runnables;

public class SharedObjectWithVolatile {
    public static void main(String[] args) {
        MyRunnableWithVolatile runnableVolatileCounter = new MyRunnableWithVolatile();
        Thread thread1 = new Thread(runnableVolatileCounter, "Thread 1");
        Thread thread2 = new Thread(runnableVolatileCounter, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
