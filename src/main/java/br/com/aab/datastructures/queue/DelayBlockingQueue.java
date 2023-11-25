package br.com.aab.datastructures.queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayWorker implements Delayed {
    private long duration;
    private String message;

    public DelayWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.duration, ((DelayWorker) o).getDuration());
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "duration=" + duration +
                ", message='" + message + '\'' +
                '}';
    }
}
public class DelayBlockingQueue {
    /**
     * This is an unbounded BlockingQueue of objects that implement the Delayed interface
     *
     * - DelayQueue keeps the elements internally until a certain delay has expired
     * - an object can only be taken from the queue when its delay has expired !!!
     *
     * We cannot place null items in the queue - the queue is sorted so that the object
     * at the head has a delay that has expired for the longest time
     *
     * If no delay has expired, then there is no head element and pools() method will return null
     *
     * size() return the count of both expired and unexpired items!!!
     */

    public static void main(String[] args) {
        BlockingQueue<DelayWorker> queue = new DelayQueue<>();
        try {
            queue.put(new DelayWorker(2000, "This is the first message ..."));
            queue.put(new DelayWorker(10000, "This is the second message ..."));
            queue.put(new DelayWorker(4500, "This is the third message ..."));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
