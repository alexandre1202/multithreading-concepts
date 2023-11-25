package br.com.aab.codechallenges.diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        if (this.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println("philosopher = " + philosopher + ", picked Up " + this + "state =" + state);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        this.lock.unlock();
        System.out.println(philosopher + "puts down " + this + " with state of " + state);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}
