package collections.container.tiny;

import collections.container.AbstractContainer;

public abstract class AbstractTiny extends AbstractContainer implements Tiny {

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        var items = this.iterator();
        var otherItems = ((AbstractTiny) obj).iterator();
        while (items.hasNext() && otherItems.hasNext()) {
            var item = items.next();
            var other = otherItems.next();
            if (!item.equals(obj)) {
                return false;
            }
        }

        return items.hasNext() == otherItems.hasNext();
    }
}
