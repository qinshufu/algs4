package collections.container;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class AbstractContainer implements Serializable {
    protected transient int modCount = 0;

    public final class ModificationChecker {
        private final int expectedModCount = modCount;

        public void check() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
