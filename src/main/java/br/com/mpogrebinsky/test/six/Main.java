package br.com.mpogrebinsky.test.six;

public class Main {
   public static void main(String [] args) {
        SharedClass sharedObject = new SharedClass();
 
        Thread thread1 = new Thread(() -> {
            while (true) {
                sharedObject.increment();
            }
        });
 
        Thread thread2 = new Thread(() -> {
            while (true) {
                sharedObject.increment();
            }
        });
 
        thread1.start();
        thread2.start();
    }

   static class SharedClass {
        private Object monitorLockInc = new Object();
        private Object monitorLockDec = new Object();
        private int counter = 0;

        public void increment() {
            synchronized(monitorLockInc) {
                this.counter++;
                System.out.printf("Incrementing with Thread %s%n", Thread.currentThread().getName());
            }
        }

        public void decrement() {
            synchronized(monitorLockDec) {
                this.counter--;
                System.out.printf("Decrementing with Thread %s%n", Thread.currentThread().getName());
            }
        }
   }
}
