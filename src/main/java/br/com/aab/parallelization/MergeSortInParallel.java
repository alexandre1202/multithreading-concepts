package br.com.aab.parallelization;

import java.util.Random;

public class MergeSortInParallel {
    private int[] inputArray;
    private int[] tempArray;

    private static Random random = new Random();

    public MergeSortInParallel(int[] inputArray) {
        this.inputArray = inputArray;
        this.tempArray = new int[inputArray.length];
    }

    public static void main(String[] args) {

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        int[] integersInParallel = createRandomArray(100000000);
        int[] integersSequential = new int[integersInParallel.length];
        for (int i = 0; i < integersSequential.length; i++)
            integersSequential[i] = integersInParallel[i];

        MergeSortInParallel inParallel = new MergeSortInParallel(integersInParallel);
        long initial = System.currentTimeMillis();
        inParallel.parallelMergeSort(0, integersInParallel.length -1, numberOfThreads);
        System.out.println("Duration for Merge Sort in parallel = " + (System.currentTimeMillis() - initial) + " ms");

        initial = System.currentTimeMillis();
        MergeSortSequential inSequential = new MergeSortSequential(integersSequential);
        inSequential.mergeSort(0, integersSequential.length - 1);
        System.out.println("Duration for Merge Sort in a sequence = " + (System.currentTimeMillis() - initial) + " ms");

    }
    private Thread createThread(int firstIndex, int lastIndex, int numberOfThreads) {
        return new Thread(() -> parallelMergeSort(firstIndex, lastIndex, numberOfThreads / 2));
    }

    private void parallelMergeSort(int firstIndex, int lastIndex, int numberOfThreads) {
        if (numberOfThreads < 2) {
            MergeSortSequential mergeSortSequential = new MergeSortSequential(this.inputArray);
            mergeSortSequential.mergeSort(firstIndex, lastIndex);
            return;
        }

        int middleIndex = (firstIndex + lastIndex) / 2;
        Thread leftSorter = createThread(firstIndex, middleIndex, numberOfThreads);
        Thread rightSorter = createThread(middleIndex + 1, lastIndex, numberOfThreads);
        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        merge(firstIndex, middleIndex, lastIndex);
    }

    private void merge(int leftIndex, int middleIndex, int rightIndex) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            this.tempArray[i] = inputArray[i];
        }

        int i = leftIndex;
        int j = middleIndex+1;
        int k = leftIndex;

        while (i <= middleIndex && j <= rightIndex) {
            if (tempArray[i] < tempArray[j]) {
                this.inputArray[k] = this.tempArray[i];
                i++;
            } else {
                this.inputArray[k] = this.tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middleIndex) {
            inputArray[k] = tempArray[i];
            k++;
            i++;
        }

        while (j < rightIndex) {
            inputArray[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public static int[] createRandomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(n);
        }
        return a;
    }
}
