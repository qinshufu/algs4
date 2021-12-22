package sort;

public class MergeSorter<T> implements Sorter<T> {
    private Comparable<T>[] clonedSequences;

    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparable<T>[] sequences) {
        this.clonedSequences = new Comparable[sequences.length];

        mergeSort(sequences, 0, sequences.length - 1);
    }

    private void mergeSort(Comparable<T>[] sequences, int low, int high) {
        if (low >= high) {
            return;
        }

        var mid = (low + high) / 2;
        mergeSort(sequences, low, mid);
        mergeSort(sequences, mid + 1, high);
        merge(sequences, low, mid, high);
    }

    private void merge(Comparable<T>[] sequences, int low, int mid, int high) {
        var sequences1Index = low;
        var sequences2Index = mid + 1;

        copyToDestFromSrc(clonedSequences, sequences, low, high - low + 1);

        for (int i = low; i <= high; i++) {
            if (sequences1Index > mid) {
                sequences[i] = clonedSequences[sequences2Index++];

            } else if (sequences2Index > high) {
                sequences[i] = clonedSequences[sequences1Index++];

            } else if (Sorter.less(clonedSequences[sequences1Index], clonedSequences[sequences2Index])) {
                sequences[i] = clonedSequences[sequences1Index++];

            } else {
                sequences[i] = clonedSequences[sequences2Index++];
            }
        }
    }

    private void copyToDestFromSrc(Comparable<T>[] destSequences, Comparable<T>[] srcSequences, int start, int length) {
        // 这里不做任何的处理判断，因为数据在这个类中由自己提供
        System.arraycopy(srcSequences, start, destSequences, start, length);
    }
}
