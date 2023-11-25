package br.com.aab.threads.timeslicing.separatedobjects;

public class SeparateObjects {

    public static void main(String[] args) {
        MyRunnableCounter runnableCounter1 = new MyRunnableCounter();
        MyRunnableCounter runnableCounter2 = new MyRunnableCounter();
        Thread thread1 = new Thread(runnableCounter1);
        Thread thread2 = new Thread(runnableCounter2);

        thread1.start();
        thread2.start();
    }
}
