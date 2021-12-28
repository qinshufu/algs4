package test.collections.container.tiny;

import collections.container.tiny.Tiny;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ConcurrentModificationException;

public abstract class TinyTest {
    private Tiny tiny;

    public abstract Class getTinyClass();

    public abstract int getCapacity();

    @Before
    public void setTiny() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        tiny = getTinyInstance();
    }

    @After
    public void clearTiny() {
        tiny = null;
    }

    @Test
    public void testConstract() {
        assertEquals(0, tiny.size());
    }

    @Test
    public void testAdd() {
        var item = "item";
        var isAdded = tiny.add(item);

        assertTrue(isAdded);
        assertEquals(1, tiny.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddItemOnTinyFull() {
        if (isTestNeverFullTiny()) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < getCapacity() + 1; i++) {
            tiny.add("item");
        }
    }

    @Test(expected = IllegalStateException.class)
    public void removeLastOnEmpty() {
        tiny.removeLast();
    }

    @Test
    public void testRemoveLast() {
        var item = "item";
        tiny.add(item);
        var removedItem = tiny.removeLast();

        assertEquals(item, removedItem);
        assertEquals(0, tiny.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, tiny.size());

        tiny.add("item");
        assertEquals(1, tiny.size());

        tiny.removeLast();
        assertEquals(0, tiny.size());
    }

    @Test
    public void testIteratorOnEmpty() {
        var iterator = tiny.iterator();
        assertEquals(false, iterator.hasNext());
        assertEquals(null, iterator.next());
    }

    @Test
    public void testIterator() {
        var item = "item";
        tiny.add(item);
        var iterator = tiny.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(item, iterator.next());
        assertEquals(false, iterator.hasNext());
        assertEquals(null, iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationIterator() {
        var iterator = tiny.iterator();
        tiny.add("item");
        iterator.hasNext();
    }

    @Test
    public void testCollectionView() {
        var item = "item";
        tiny.add(item);
        var collection = tiny.collectionView();
        var iterator = collection.iterator();

        assertEquals(1, collection.size());
        assertTrue(iterator.hasNext());
        assertEquals(item, iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void testCollectionViewEmpty() {
        var collection = tiny.collectionView();

        assertEquals(0, collection.size());
        assertFalse(collection.iterator().hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationCollectionView() {
        var collection = tiny.collectionView();
        tiny.add("item");
        collection.contains("balabala");
    }

    private @NotNull Tiny getTinyInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class tinyClass = getTinyClass();
        Constructor tinyConstructor;
        Tiny tiny;

        if (isTestNeverFullTiny()) {
            tinyConstructor = tinyClass.getConstructor();
            tiny = (Tiny) tinyConstructor.newInstance();

        } else {
            tinyConstructor = tinyClass.getConstructor(int.class);
            tiny = (Tiny) tinyConstructor.newInstance(getCapacity());
        }

        return tiny;
    }

    private boolean isTestNeverFullTiny() {
        Class tinyClass = getTinyClass();
        try {
            tinyClass.getConstructor(int.class);

        } catch (NoSuchMethodException e) {
            return true;
        }

        return false;
    }
}
