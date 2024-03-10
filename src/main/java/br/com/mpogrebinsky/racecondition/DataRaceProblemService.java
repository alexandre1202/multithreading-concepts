package br.com.mpogrebinsky.racecondition;

public class DataRaceProblemService {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedObject.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedObject.checkDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }
    private static class SharedObject {
        private volatile int x = 0;    //volatile solve the Data Race and increase the Latency
        private volatile int y = 0;    //volatile solve the Data Race and increase the Latency
        public void increment() {
            x++;
            y++;
        }

        public void checkDataRace() {
            if (y > x)
                System.out.printf("Data race happening due to y is [%d] and x is [%d]%n", this.y, this.x);
        }
    }
}
