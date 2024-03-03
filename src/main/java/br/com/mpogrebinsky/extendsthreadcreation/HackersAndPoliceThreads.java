package br.com.mpogrebinsky.extendsthreadcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackersAndPoliceThreads {
    public static final int MAX_PASSWORD = 999;

    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        List<Thread> threads = new ArrayList<>() {{
            add(new AscendingHackerThread(vault));
            add(new DescendingHackerThread(vault));
            add(new PoliceThread());
        }};
        threads.forEach(Thread::start);
    }
    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.printf("Starting thread [%s]%n", this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.printf("The AscendingHackerThread [%s] guessed the password [%d]%n", this.getName(), guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread{
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.printf("The DescendingHackerThread [%s] guessed the password [%d]%n", this.getName(), guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("" + i);
            }
            System.out.println("Game over for you hackers!");
            System.exit(0);
        }
    }
}
