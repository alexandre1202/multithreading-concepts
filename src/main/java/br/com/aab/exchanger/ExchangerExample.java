package br.com.aab.exchanger;

import java.util.concurrent.Exchanger;

class IncrementExchanger implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public IncrementExchanger(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("Incrementing the counter = " + counter);
            try {
                counter = exchanger.exchange(counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("counter incremented and changed by the exchanger " + counter);
        }
    }
}

class DecrementExchanger implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public DecrementExchanger(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("Decrementing the counter = " + counter);
            try {
                counter = exchanger.exchange(counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("counter decremented and changed by the exchanger " + counter);
        }
    }
}
public class ExchangerExample {
    /**
     * Exchanger allow two threads exchange objects exclusively but not for multi-thread
     *          ---> Object #1 --->
     * Thread 1 ---> Object #1 --->  Thread 2
     *          ---> Object #1 --->
     */

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        IncrementExchanger increment = new IncrementExchanger(exchanger);
        DecrementExchanger decrement = new DecrementExchanger(exchanger);
        new Thread(increment).start();
        new Thread(decrement).start();
    }
}
