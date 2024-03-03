package br.com.aab.threads.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class UUIDGenerator implements Callable<String> {
    private int id;

    public UUIDGenerator(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "Id:" + this.id + " - UUID" + UUID.randomUUID();
    }
}
public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> uuids = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            uuids.add(executor.submit(new UUIDGenerator(i)));
        }
        uuids.stream().forEach(e -> {
            try {
                System.out.println("Id / UUID = " + e.get());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        });
        executor.shutdown();
    }
}
