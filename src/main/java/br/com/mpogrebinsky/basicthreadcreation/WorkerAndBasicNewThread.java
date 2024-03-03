package br.com.mpogrebinsky.basicthreadcreation;

public class WorkerAndBasicNewThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->
                System.out.printf("new Thread called [%s] ...%n", Thread.currentThread().getName()));

        System.out.printf("This is %s thread before to start a new Thread %n", Thread.currentThread().getName());
        thread.setName("New Child thread");
        thread.start();
        System.out.printf("This is %s thread after to start a new Thread %n", Thread.currentThread().getName());
        Thread.sleep(1000);
    }
}
