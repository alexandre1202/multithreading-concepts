package br.com.aab.codechallenges.diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosopherExecutor {
    /**
     * The aim of the simulation is that it is possible to avoid thread starvation
     * - all the threads are going to be executed by the executor service
     * - that we are able to avoid deadlocks because we use tryLock()
     */

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
        Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
        Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

        try {
            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) chopsticks[i] = new Chopstick(i);
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1) % Constants.NUMBER_OF_PHILOSOPHERS]);
                executor.execute(philosophers[i]);
            }
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
            for (Philosopher philosopher: philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            executor.shutdown();
            while (! executor.isTerminated()) Thread.sleep(1000);

            for (Philosopher philosopher: philosophers) {
                System.out.println(philosopher + " eat #" + philosopher.getEatingCounter() + " times");
            }


        }
    }
}
