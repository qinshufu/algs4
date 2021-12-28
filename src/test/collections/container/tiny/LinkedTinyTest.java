package test.collections.container.tiny;

import collections.container.tiny.LinkedTiny;

public class LinkedTinyTest extends TinyTest {
    private static final Class<LinkedTiny> LINKED_TINY_CLASS = LinkedTiny.class;
    private static final int CAPCITY = -1;

    @Override
    public Class getTinyClass() {
        return LINKED_TINY_CLASS;
    }

    @Override
    public int getCapacity() {
        return CAPCITY;
    }
}
