package collections.container.stack;

import java.util.Iterator;

public class ArrayStack extends AbstractStack {
    private final Object[] items;
    private int itemsCount;
    private final static int DEFAULT_CAPCITY = 100;

    ArrayStack() {
        this(DEFAULT_CAPCITY);
    }

    ArrayStack(int initCapcity) {
        items = new Object[initCapcity];
    }

    @Override
    public void push(Object item) {
        checkValue(item);
        if (isFull()) {
            throw new IllegalArgumentException("Error: Stack is full.");
        }

        items[itemsCount++] = item;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: Stack is empty.");
        }

        var removedValue = items[itemsCount - 1];
        itemsCount--;
        return removedValue;
    }

    @Override
    public Object peek() {
        return items[itemsCount - 1];
    }

    @Override
    public boolean isEmpty() {
        return itemsCount == 0;
    }

    @Override
    public boolean isFull() {
        return itemsCount >= items.length;
    }

    @Override
    public int size() {
        return 0;
    }

    private void checkValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Error: Argument is null.");
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index;
            private final ModificationChecker modChecker = new ModificationChecker();

            @Override
            public boolean hasNext() {
                modChecker.check();
                return index < itemsCount;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return items[index++];
                }

                return null;
            }
        };
    }

}
