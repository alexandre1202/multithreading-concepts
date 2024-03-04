package br.com.mpogrebinsky.termination;

public class TerminationThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepingThread());
        thread.start();
        thread.interrupt();
    }
    private static class SleepingThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.printf("InterruptedException before sleep %s%n", Thread.currentThread().isInterrupted());
                    Thread.sleep(1000000);
                    System.out.printf("InterruptedException after sleep %s%n", Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.printf("InterruptedException catch %s%n", Thread.currentThread().isInterrupted());
                    return;
                }
            }
        }
    }
}
