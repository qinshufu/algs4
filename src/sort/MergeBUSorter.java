package sort;

import java.util.Arrays;

/**
 * 归并排序的非递归版本
 */

public class MergeBUSorter<T> implements Sorter<T> {
    private Comparable<T>[] clonedSequences;

    @Override
    public void sort(Comparable<T>[] sequences) {
        this.clonedSequences = new Comparable[sequences.length];
        mergeSort(sequences);
    }

    private void mergeSort(Comparable<T>[] sequences) {
        var maxSubArraySize = sequences.length - 1;
        var maxSubArrayIndex = maxSubArraySize - 1;

        for (int arraySize = 1; arraySize <= maxSubArraySize; arraySize += arraySize) {
            for (int arrayIndex = 0; arrayIndex <= maxSubArrayIndex; arrayIndex += arraySize * 2) {
                var arrayMidIndex = arrayIndex + arraySize - 1;
                var arrayHighIndex = Math.min(arrayIndex + arraySize * 2 - 1, sequences.length - 1);
                merge(sequences, arrayIndex, arrayMidIndex, arrayHighIndex);
            }
        }

    }

    private void merge(Comparable<T>[] sequences, int low, int mid, int high) {
        if (low >= high) {
            return;
        }

        var mergedArrayLength = high - low + 1;
        System.arraycopy(sequences, low, clonedSequences, low, mergedArrayLength);

        var leftIndex = low;
        var rightIndex = mid + 1;
        for (int i = low; i <= high; i++) {
            if (leftIndex > mid) {
                sequences[i] = this.clonedSequences[rightIndex++];

            } else if (rightIndex > high) {
                sequences[i] = this.clonedSequences[leftIndex++];

            } else if (Sorter.less(this.clonedSequences[leftIndex], this.clonedSequences[rightIndex])) {
                sequences[i] = this.clonedSequences[leftIndex++];

            } else {
                sequences[i] = this.clonedSequences[rightIndex++];
            }
        }
    }

    public static void main(String[] args) {
        var nums = new Double[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = Double.parseDouble(args[i]);
        }

        var sorter = new MergeBUSorter<Double>();
        sorter.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}