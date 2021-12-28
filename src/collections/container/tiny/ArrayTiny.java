package collections.container.tiny;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class ArrayTiny extends AbstractTiny {

    private static final int DEFAULT_CAPACITY = 100;
    private final Object[] items;
    private int listsize;

    public ArrayTiny() {
        items = new Object[DEFAULT_CAPACITY];
    }

    public ArrayTiny(int capacity) {
        items = new Object[capacity];
    }

    @Override
    public boolean add(Object obj) {
        if (listsize >= items.length) {
            throw new IllegalStateException("Error: List is full.");
        }

        items[listsize++] = obj;
        modCount++;
        return true;
    }

    @Override
    public Object removeLast() {
        if (listsize == 0) {
            throw new IllegalStateException("Error: List is empty.");
        }

        var removedValue = items[--listsize];
        modCount++;
        return removedValue;
    }

    @Override
    public int size() {
        return listsize;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private final ModificationChecker modChecker = new ModificationChecker();
            private int index;

            @Override
            public boolean hasNext() {
                modChecker.check();
                return index < size();
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

    @Override
    public Collection collectionView() {
        return new AbstractCollection() {
            private ModificationChecker modChecker = new ModificationChecker();

            @Override
            public Iterator iterator() {
                modChecker.check();
                return ArrayTiny.this.iterator();
            }

            @Override
            public int size() {
                modChecker.check();
                return ArrayTiny.this.size();
            }
        };
    }

}
