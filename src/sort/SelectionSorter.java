package sort;

public class SelectionSorter<T> implements Sorter<T> {
    @Override
    public void sort(Comparable<T>[] sequences) {
        for (int i = 0; i < sequences.length; i++) {
            var minObjIndex = i;
            for (int j = i + 1; j < sequences.length; j++) {
                if (Sorter.less(sequences[j], sequences[minObjIndex])) {
                    minObjIndex = j;
                }
            }

            Sorter.swap(sequences, i, minObjIndex);
        }
    }

}
