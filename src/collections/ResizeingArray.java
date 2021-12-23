package collections;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ResizeingArray<T> {
    private Object[] items;
    private static final int DEFAULT_INIT_SIZE = 5;
    private static final double MAX_FREE_SPACE_RATIO = 1.0 / 2;
    private int logicalSize;
    private int length;

    ResizeingArray() {
        this(DEFAULT_INIT_SIZE);
    }

    ResizeingArray(int initSize) {
        items = new Object[initSize];

        logicalSize = 0;
        length = initSize;
    }

    public void add(T value) {
        resizeIfNeeded();

        items[logicalSize++] = value;
    }

    @SuppressWarnings("unchecked")
    public T remove() throws EmptyArrayException {
        if (logicalSize < 1) {
            throw new EmptyArrayException();
        }

        logicalSize--;
        resizeIfNeeded();

        return (T) items[logicalSize + 1];
    }

    public void remove(T value) {
        for (int i = 0; i < logicalSize; i++) {
            if (items[i] == value) {
                moveForwardOneStepAt(i);
            }
        }

        resizeIfNeeded();
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        if (index > logicalSize - 1) {
            throw new IndexOutOfBoundsException();
        }

        var removedValue = (T) items[index];
        moveForwardOneStepAt(index);

        return removedValue;
    }

    public void insert(int index, T value) {
        if (index >= 0 && index <= logicalSize) {
            moveForwardOneStepAt(index);

            items[index] = value;
        } else if (index == logicalSize) {
            items[index] = value;
        }

        throw new IndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return logicalSize == 0;
    }

    public void scale() {
        resize(logicalSize);
    }

    private void moveForwardOneStepAt(int index) {
        for (int i = index; i < logicalSize + 1; i++) {
            items[i] = items[i + 1];
        }

        logicalSize--;
    }

    private void resizeIfNeeded() {
        if (logicalSize <= 0) {
            return;
        }

        var freeSapceRatio = length - logicalSize;
        if (freeSapceRatio < 1 || freeSapceRatio * 1.0 / length > MAX_FREE_SPACE_RATIO) {
            resize(lookForNewArraySize());
        }
    }

    private int lookForNewArraySize() {
        var newSize = (int) (logicalSize * 1.5);
        return Math.max(newSize, logicalSize + DEFAULT_INIT_SIZE);
    }

    private void resize(int size) {
        var newItems = Arrays.copyOf(items, size);
        items = newItems;
        length = size;
    }

    public static void main(String... args) throws EmptyArrayException {
        var lenght = 1000;
        var values = new Integer[lenght];
        for (int i = 0; i < lenght; i++) {
            values[i] = StdRandom.uniform(lenght);
        }

        var ra = new ResizeingArray<Integer>();
        for (var value : values) {
            ra.add(value);
        }

        for (int i = 0; i < lenght; i++) {
            var value = ra.remove();
            assert value.equals(values[lenght - 1 - i]);
        }

        StdOut.println("PASS");
    }
}
