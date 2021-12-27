package collections.container.stack;

import java.util.Iterator;

public interface Stack extends Iterable {
    void push(Object item);

    Object pop();

    Object peek();

    boolean isEmpty();

    boolean isFull();

    int size();
}
