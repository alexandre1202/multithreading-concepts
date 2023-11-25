package br.com.aab.datastructures.copyingdata;

import java.util.List;

public class ReadTask implements Runnable {
    private List<Integer> list;
    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list);
            System.out.println("----------------------------------");
        }
    }
}
