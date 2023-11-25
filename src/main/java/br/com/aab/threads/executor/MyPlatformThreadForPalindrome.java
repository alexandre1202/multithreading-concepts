package br.com.aab.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPlatformThreadForPalindrome {
    public static void main(String[] args) {
        final Long init = System.currentTimeMillis();
        final Integer count = 10_000_000;

        Runnable palindromeChecker = () -> isPalindrome("natan");
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++)
            executorService.execute(palindromeChecker);

        executorService.shutdown();
    }

    private static boolean isPalindrome(String palindrome) {
        //System.out.println("Thread id: " + Thread.currentThread().threadId());
        char[] chars = palindrome.toLowerCase().toCharArray();
        int i1 = 0;
        int i2 = chars.length-1;
        while (i2 > i1) {
            if (chars[i2] != chars[i1]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
}
