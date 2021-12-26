package collections.container.tiny;

import java.util.Collection;
import java.util.Iterator;

public interface Tiny {
    boolean add(Object obj);

    Object removeLast();

    int size();

    Iterator iterator();

    Collection collectionView();
}
