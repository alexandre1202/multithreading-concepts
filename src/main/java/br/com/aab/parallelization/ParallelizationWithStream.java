package br.com.aab.parallelization;

import java.util.stream.LongStream;

public class ParallelizationWithStream {
    public static void main(String[] args) {
        ParallelizationWithStream exec = new ParallelizationWithStream();
        long n = 10_000_000_000L;

        long init = System.currentTimeMillis();
        exec.sumSequential(n);
        System.out.println("Summing " + n + " numbers in sequential = " + (System.currentTimeMillis() - init) + " ms");

        init = System.currentTimeMillis();
        exec.sumInParallel(n);
        System.out.println("Summing " + n + " numbers in parallel = " + (System.currentTimeMillis() - init) + " ms");
    }

    private Long sumSequential(long n) {
        return LongStream.rangeClosed(0, n).reduce(0L, Long::sum);
    }

    private Long sumInParallel(long n) {
        return LongStream.rangeClosed(0, n).parallel().reduce(0L, Long::sum);
    }
}
