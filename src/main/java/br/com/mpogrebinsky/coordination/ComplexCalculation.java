package br.com.mpogrebinsky.coordination;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread threadResult1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread threadResult2 = new PowerCalculatingThread(base2, power2);
        threadResult1.start();
        threadResult2.start();
        try {
            threadResult1.join();
            threadResult2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result = threadResult1.getResult().add(threadResult2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            this.result = buildPower(base, power);
        }

        private BigInteger buildPower(BigInteger base, BigInteger power) {
            BigInteger powerResult = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                powerResult = powerResult.multiply(base);
            }
            return powerResult;
        }

        public BigInteger getResult() { return result; }
    }
    
}
