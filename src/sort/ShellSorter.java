package sort;

public class ShellSorter<T> implements Sorter<T> {
    @Override
    public void sort(Comparable<T>[] sequences) {
        var h = selectHFrom(sequences);
        while (h > 0) {
            for (int i = h; i < sequences.length; i += h) {
                for (int j = i; j < sequences.length; j += h) {
                    if (Sorter.less(sequences[j - h], sequences[j])) {
                        break;
                    }

                    Sorter.swap(sequences, j - h, j);
                }
            }

            h = nextHFrom(h);
        }
    }

    private int selectHFrom(Comparable<T>[] sequences) {
        var h = 1;
        while (h < sequences.length) {
            h = h * 3 + 1;
        }

        return h;
    }

    private int nextHFrom(int h) {
        return (h - 1) / 3;
    }

}
