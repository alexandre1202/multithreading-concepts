package br.com.aab.threads.timeslicing;

import java.util.stream.Stream;

public class RunnableLambdaBasic {
    public static void main(String[] args) {
        Runnable runnable1 = () -> printLoopUpTo(30, "Runner1");
        Runnable runnable2 = () -> printLoopUpTo(30, "Runner2");

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();
    }
    public static void printLoopUpTo(final int limit, final String runnerName) {
        Stream.iterate(1L, n -> n + 1).limit(limit).toList()
                .forEach(n -> System.out.println("Runner: " + runnerName + " - iteration: " + n));
    }
}