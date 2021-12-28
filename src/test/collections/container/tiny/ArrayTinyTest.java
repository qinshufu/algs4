package test.collections.container.tiny;

import collections.container.tiny.ArrayTiny;

public class ArrayTinyTest extends TinyTest {
    private static final Class<ArrayTiny> ARRAY_TINY_CLASS = ArrayTiny.class;
    private static final int CAPCITY = 10;

    @Override
    public Class getTinyClass() {
        return ARRAY_TINY_CLASS;
    }

    @Override
    public int getCapacity() {
        return CAPCITY;
    }
}
