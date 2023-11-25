package br.com.aab.datastructures.copyingdata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentArray {
    private List<Integer> list = new CopyOnWriteArrayList<>();
    public ConcurrentArray() {
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
    }
    public void simulate() {
        Thread t1 = new Thread(new WriteTask(list));
        Thread t2 = new Thread(new WriteTask(list));
        Thread t3 = new Thread(new WriteTask(list));
        Thread t4 = new Thread(new ReadTask(list));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
