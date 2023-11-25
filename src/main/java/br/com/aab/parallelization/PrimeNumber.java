package br.com.aab.parallelization;

import java.util.stream.IntStream;

public class PrimeNumber {

    public static void main(String[] args) {
        PrimeNumber prime = new PrimeNumber();
        int maximumInteger = Integer.MAX_VALUE/100;
        long initial = System.currentTimeMillis();
        long sequentialPrimes = IntStream.rangeClosed(2, maximumInteger)
                .filter(prime::isPrimeNumber)
                .count();
        System.out.println("Duration to count the sequence of " + sequentialPrimes + " prime numbers between zero to " + maximumInteger + "  is " + (System.currentTimeMillis() - initial));

        initial = System.currentTimeMillis();
        sequentialPrimes = IntStream.rangeClosed(2, maximumInteger)
                .parallel()
                .filter(prime::isPrimeNumber)
                .count();
        System.out.println("Duration to count " + sequentialPrimes + " prime numbers in parallel, between zero to " + maximumInteger + "  is " + (System.currentTimeMillis() - initial));

    }
    private boolean isPrimeNumber(int n) {
        if (n <2)   return false;
        if (n==2)   return false;
        if (n%2==0) return false;

        long maxDivisor = (long) Math.sqrt(n);
        for (int i = 3; i <= maxDivisor; i+=2) {
            if (n%i==0) return false;
        }
        return true;
    }
}
