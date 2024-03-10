package br.com.mpogrebinsky.racecondition;

public class DataRaceProblemService {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) sharedObject.increment();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) sharedObject.checkDataRace();
        }).start();
    }
    private static class SharedObject {
        private int x = 0;    //volatile solve the Data Race but with a performance penalty
        private int y = 0;    //volatile solve the Data Race but with a performance penalty
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
