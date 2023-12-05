package br.com.aab.threads.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of
 * operations being performed by other tasks
 * - you give a initial count to a CountDownLatch object, and any task that call await() on the object
 *   will block until the count reaches zero
 * - Other tasks may call countDown() on the object to reduce the count, presumably when a task finishes
 *   its job
 * - if you need a version that resets the count, youy can use a Cyclic Barrier instead
 * - the tasks that call countDown() are not blocked when they make that call. Only the call to await()
 *   is blocked until the count reaches zero
 *
 * A typical use-case is to divide a problem into N independently solvable tasks and create a CountDownLatch()
 * with a value of N
 *
 * When tasks is finished it call countDown on the latch. Thas waiting for the problem to be solve call await()
 * on the latch to hold themselves back until it is completed.
 */

class Worker implements Runnable {
    private int id;
    private CountDownLatch latch;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    private void doWork() {
        try {
            System.out.println("Thead with ID " + this.id
                    + " starts working on Thread Id " + Thread.currentThread().threadId());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            service.execute(new Worker(i, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks have been finished ...");
        service.shutdown();
    }
}
