package br.com.mpogrebinsky.coordination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CoordinationWithFactorial {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputs = List.of(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        for(long n: inputs) {
            threads.add(new FactorialThread(n));
        }

        for(Thread thread: threads) {
            thread.start();
        }

        for(Thread thread: threads) {
            thread.join();
        }

        for(int i = 0; i < inputs.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.printf("Factorial of [%s] is [%d]%n",
                        threads.get(i).getName(), factorialThread.getResult());
            } else {
                System.out.printf("The calculation for %d is still in progress ...%n", inputs.get(i));
            }
        }
    }

    private static class FactorialThread extends Thread {
        private long input;
        private BigInteger result = BigInteger.ONE;
        private boolean isFinished = false;

        public FactorialThread(long input) {
            this.input = input;
        }

        @Override
        public void run() {
            this.result = factorial(input);
            this.isFinished = true;
        }

        private BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
//                System.out.printf("Calculating the factorial of [%d] result [%d] for Thread [%d]%n", i, tempResult, Thread.currentThread().threadId());
            }
//            System.out.printf("Result of Thread [%d] is [%d]%n", Thread.currentThread().threadId(), tempResult);
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
