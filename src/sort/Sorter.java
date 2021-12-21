package sort;

public interface Sorter<T> {
    public abstract void sort(Comparable<T>[] sequences);

    public static void swap(Object[] sequences, int a, int b) {
        Object temp = sequences[a];
        sequences[a] = sequences[b];
        sequences[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] sequences) {
        for (int i = 0; i + 1 < sequences.length; i++) {
            if (less(sequences[i + 1], sequences[i])) {
                return false;
            }
        }

        return true;
    }
}
