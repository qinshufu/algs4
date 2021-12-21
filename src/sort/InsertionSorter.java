package sort;

public class InsertionSorter<T> implements Sorter<T> {
    @Override
    public void sort(Comparable<T>[] sequences) {
        for (int i = 1; i < sequences.length; i++) {
            for (int j = i; j > 0; j--) {
                if (Sorter.less(sequences[j], sequences[j - 1])) {
                    break;
                }

                Sorter.swap(sequences, j, j - 1);
            }
        }
    }
}
