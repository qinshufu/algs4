package collections.container.tiny;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;


public class LinkedTiny extends AbstractTiny {
    private static class OneWayNode {
        Object value;
        OneWayNode next;

        OneWayNode(Object value, OneWayNode next) {
            this.value = value;
            this.next = next;
        }
    }

    private OneWayNode linkedlist;
    private int listsize;

    LinkedTiny() {
        // pass
    }

    LinkedTiny(Iterator items) {
        while (items.hasNext()) {
            add(items.next());
        }
    }

    @Override
    public boolean add(Object obj) {
        var node = new OneWayNode(obj, null);
        if (size() == 0) {
            linkedlist = node;

        } else {
            var lastNode = findLastNode();
            lastNode.next = node;
        }

        listsize++;
        modCount++;
        return true;
    }


    @Override
    public Object removeLast() {
        if (size() == 0) {
            return null;
        }

        Object removedValue = null;
        if (size() == 1) {
            removedValue = linkedlist.value;
            linkedlist = null;

        } else {
            var prevLastNode = linkedlist;
            while (prevLastNode.next.next != null) {
                prevLastNode = prevLastNode.next;
            }

            removedValue = prevLastNode.next.value;
        }

        listsize++;
        modCount++;
        return removedValue;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private final ModificationChecker checker = new ModificationChecker();
            private OneWayNode node = linkedlist;

            @Override
            public boolean hasNext() {
                checker.check();
                return node != null;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    var value = node.value;
                    node = node.next;
                    return value;
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
                return LinkedTiny.this.iterator();
            }

            @Override
            public int size() {
                modChecker.check();
                return LinkedTiny.this.size();
            }
        };
    }


    private OneWayNode findLastNode() {
        assert size() != 0;

        var lastNode = linkedlist;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        return lastNode;
    }

}
