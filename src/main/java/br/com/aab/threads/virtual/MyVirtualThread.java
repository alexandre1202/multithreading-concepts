package br.com.aab.threads.virtual;

import java.util.ArrayList;
import java.util.List;

public class MyVirtualThread {

//    public static void main(String[] args) {
//        final Long init = System.currentTimeMillis();
//        final Integer count = 10_000_000;
//        List<Thread> vThreads = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            Thread vThread = Thread.ofVirtual().start(() -> {
//                isPalindrome("Natan");
//            });
//            vThreads.add(vThread);
//        }
//
//        for (int i = 0; i < vThreads.size(); i++) {
//            try {
//                vThreads.get(i).join();
//            } catch (InterruptedException ie) {
//                throw new RuntimeException(ie);
//            }
//        }
//        System.out.println("Execution of [" + count +  "] Virtual Thread in [" + ((System.currentTimeMillis() - init) / 1000) + "] seconds");
//    }

    private static boolean isPalindrome(String palindrome) {
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
