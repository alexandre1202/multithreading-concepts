package br.com.aab.threads.timeslicing.runnables;

public class MyRunnable {

    public static void main(String[] args) {
        Thread myThread = new Thread( () -> {
            System.out.println("MyRunnable lambda started");
            System.out.println("MyRunnable lambda finished");
        });
        myThread.start();

        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("threadName [" + threadName + "] is running");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            System.out.println(" finished");
        };

        Thread thread = new Thread( runnable, "my lambda Runnable Thread" );
        thread.start();
    }
}
