package br.com.mpogrebinsky.racecondition;

public class InventoryService {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter counter = new InventoryCounter();
        IncrementingInventory increment = new IncrementingInventory(counter);
        DecrementingInventory decrement = new DecrementingInventory(counter);

        increment.start();
        decrement.start();

        increment.join();
        decrement.join();

        System.out.printf("The counter is %d%n", counter.getItems());
    }

    public static class IncrementingInventory extends Thread {
        private InventoryCounter inventoryCounter;
        public IncrementingInventory(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                this.inventoryCounter.increment();
            }
        }
    }

    public static class DecrementingInventory extends Thread {
        private InventoryCounter inventoryCounter;
        public DecrementingInventory(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                this.inventoryCounter.decrement();
            }
        }
    }

    public static class InventoryCounter {
        private int items = 0;
        public void increment() {
            items++;
        }

        public void decrement() {
            items--;
        }

        public int getItems() {
            return this.items;
        }
    }
}
