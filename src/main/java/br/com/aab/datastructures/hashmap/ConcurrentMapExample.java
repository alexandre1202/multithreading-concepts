package br.com.aab.datastructures.hashmap;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentMapExample {
    /**
     * Example of a Synchronized and sorted Map
     */

    public static void main(String[] args) {
        // Creating a concurrent TreeMap
        Map<String, Integer> concurrentMap = new ConcurrentSkipListMap<>();

        // Adding elements in a thread-safe manner
        concurrentMap.put("One", 1);
        concurrentMap.put("A", 0);
        concurrentMap.put("Two", 2);
        concurrentMap.put("Three", 3);

        // Performing operations on the map
        System.out.println("Size: " + concurrentMap.size());
        System.out.println("Value for key 'Two': " + concurrentMap.get("Two"));

        // Iterating over the map
        for (Map.Entry<String, Integer> entry : concurrentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
