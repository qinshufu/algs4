package collections.container.tiny;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class ArrayTiny extends AbstractTiny {

    private final static int DEFAULT_CAPCITY = 100;
    private final Object[] items;
    private int listsize;

    ArrayTiny() {
        items = new Object[DEFAULT_CAPCITY];
    }

    ArrayTiny(int initCapcity) {
        items = new Object[initCapcity];
    }

    @Override
    public boolean add(Object obj) {
        if (listsize + 1 >= items.length) {
            return false;
        }

        items[listsize++] = obj;
        modCount++;
        return true;
    }

    @Override
    public Object removeLast() {
        if (listsize < 1) {
            return null;
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
