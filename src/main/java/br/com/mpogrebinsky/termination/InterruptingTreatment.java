package br.com.mpogrebinsky.termination;

import java.math.BigInteger;

public class InterruptingTreatment {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(
                new BigInteger("20"), new BigInteger("100000")));
        thread.start();
        try {
            // If we sleep for 2000 milli sec the Thread managed to compute the pow()
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private record LongComputationTask(BigInteger base, BigInteger power) implements Runnable {
        @Override
        public void run() {
            System.out.printf("The base [%d] pow by %d = [%d]%n",
                    base, power, pow(base, power));
        }
        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation due delay to run the pow for base = " + base + ", power = " + power);
                    return i;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
