package collections.container.stack;

import collections.container.AbstractContainer;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractStack extends AbstractContainer implements Stack {
    @Override
    public String toString() {
        var itemsString = new StringBuilder();
        for (var item : this) {
            itemsString.append(item);
            itemsString.append(", ");
        }

        if (itemsString.length() > 0) {
            itemsString.delete(-2, -1);
        }

        return this.getClass() + "[" + itemsString + "]";
    }

    public Object[] toArray() {
        // 小小偷个懒，直接用 ArrayList😂
        var items = new ArrayList<Object>();
        for (var item : this) {
            items.add(item);
        }

        return items.toArray();
    }

    public Collection collectionView() {
        return new AbstractCollection() {
            @Override
            public Iterator iterator() {
                return this.iterator();
            }

            @Override
            public int size() {
                return this.size();
            }
        };
    }

}
