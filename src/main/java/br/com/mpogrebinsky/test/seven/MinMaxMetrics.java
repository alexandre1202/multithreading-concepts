package br.com.mpogrebinsky.test.seven;

public class MinMaxMetrics {
    private volatile long min;
    private volatile long max;
    public MinMaxMetrics() {
        // Add code here
        this.min = Long.MAX_VALUE;
        this.max = Long.MIN_VALUE;
    }
    public void addSample(long newSample) {
        synchronized (this) {
            this.min = Math.min(newSample, this.min);
            this.max = Math.max(newSample, this.max);
        }
    }
    public long getMin() {
        return this.min;
    }
    public long getMax() {
        return this.max;
    }
}
