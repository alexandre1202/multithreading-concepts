package br.com.aab.datastructures.copyingdata;

public class CopyOnWriteExample {
    /**
     * Instead of Collections.synchronizedList() or other method of Collections we can use CopyAndWrite data when
     * we need to read the data without any locking.
     */

    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray();
        concurrentArray.simulate();
    }
}
