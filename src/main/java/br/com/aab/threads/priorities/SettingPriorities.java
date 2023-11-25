package br.com.aab.threads.priorities;

class Worker implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i<10; i++) System.out.println("Running " + i);
    }
}
public class SettingPriorities {
    public static void main(String[] args) {
        Thread t = new Thread(new Worker());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        System.out.println("This is in the main Thread with normal Priority : " + Thread.currentThread().getPriority());
    }
}
