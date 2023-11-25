package br.com.aab.threads.locks.reentrant;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Processor{
    private final List<UUID> uuids = new LinkedList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void produceUUID() throws InterruptedException {
        lock.readLock().lock();
        try {
            uuids.addAll(buildUUIDs());
        } finally {
            lock.readLock().unlock();
        }
    }
    public void consumeUUID() throws InterruptedException {
        lock.readLock().lock();
        try {
            uuids.addAll(buildUUIDs());
        } finally {
            lock.readLock().unlock();
        }
    }
    private List<UUID> buildUUIDs() {
        return Stream.iterate(UUID.randomUUID(), uuid -> UUID.randomUUID()).limit(10).toList();
    }
}
public class PractisingReentrantReadAndWriteLock {

    public static void main(String[] args) {

    }
}
