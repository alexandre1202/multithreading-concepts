package br.com.aab.threads.locks.reentrant;

public class ReentrancyForIntrinsicLocks {
    public synchronized void doSomething() {
        System.out.printf("Running Parent doSomething ... ");
    }
}

class SubReentrancyForIntrinsicLocks extends ReentrancyForIntrinsicLocks{
    public synchronized void doSomething() {
        System.out.printf("Calling doSomething()%n");
        super.doSomething();
    }

    public static void main(String[] args) {
        SubReentrancyForIntrinsicLocks sub = new SubReentrancyForIntrinsicLocks();
        sub.doSomething();
    }
}
