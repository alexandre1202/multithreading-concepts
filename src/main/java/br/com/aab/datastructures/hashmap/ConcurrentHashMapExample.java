package br.com.aab.datastructures.hashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class ProducerConcurrentHashMapWorker implements Runnable{
    private ConcurrentMap<String, Integer> map;
    public ProducerConcurrentHashMapWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }
    @Override
    public void run() {
        try {
            map.put("Z", ((int)'Z'));
            Thread.sleep(1000);
            map.put("B", ((int)'B'));
            map.put("A", ((int)'A'));
            Thread.sleep(1000);
            map.put("D", ((int)'D'));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ConsumerConcurrentHashMapWorker implements Runnable{
    private ConcurrentMap<String, Integer> map;
    public ConsumerConcurrentHashMapWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("ASCII value for Z" + map.get("Z"));
            Thread.sleep(2000);
            System.out.println("ASCII value for B" + map.get("B"));
            System.out.println("ASCII value for A" + map.get("A"));

            Thread.sleep(1000);
            map.put("D", ((int)'D'));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        ProducerConcurrentHashMapWorker producer = new ProducerConcurrentHashMapWorker(map);
        ConsumerConcurrentHashMapWorker consumer = new ConsumerConcurrentHashMapWorker(map);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
