package br.com.aab.threads.timeslicing.separatedobjects;

public class MyRunnableCounter implements Runnable {

    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            this.count++;
        }
        System.out.println(Thread.currentThread().getName() + " : " + this.count);
    }
}
