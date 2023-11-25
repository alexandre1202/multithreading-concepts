package br.com.aab.parallelization;

public class MergeSortSequential {
    private int[] inputArray;
    private int[] tempArray;

    public MergeSortSequential(int[] inputArray) {
        this.inputArray = inputArray;
        this.tempArray = new int[this.inputArray.length];
    }

    private void sort() {
        mergeSort(0, this.inputArray.length-1);
    }

    public void mergeSort(int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return;
        int middleIndex = (leftIndex + rightIndex) / 2;
        mergeSort(leftIndex, middleIndex);
        mergeSort(middleIndex+1, rightIndex);
        merge(leftIndex, middleIndex, rightIndex);
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

    public void showArray() {
        for (int i = 0; i < this.inputArray.length; i++) {
            System.out.print(this.inputArray[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] input = {5, -1, 0, 7, 2, 3, 2, 1, 0, 1, 2};
        MergeSortSequential merge = new MergeSortSequential(input);
        merge.sort();
        merge.showArray();
        System.out.println("\n+ ------------------------------------------------- +");

        int[] input1 = {-1, 0, -1, 2};
        merge = new MergeSortSequential(input1);
        merge.sort();
        merge.showArray();
        System.out.println("\n+ ------------------------------------------------- +");

        int[] input2 = {10, 0, -1, -10, 3, 0, 10, -1};
        merge = new MergeSortSequential(input2);
        merge.sort();
        merge.showArray();

    }
}
