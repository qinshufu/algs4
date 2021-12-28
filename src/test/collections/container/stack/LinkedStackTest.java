package test.collections.container.stack;

import collections.container.stack.LinkedStack;

public class LinkedStackTest extends StackTest {
    private static final Class STACK_CLASS = LinkedStack.class;
    private static final int STACK_CAPCITY = -1;

    @Override
    public Class getStackClass() {
        return STACK_CLASS;
    }

    @Override
    public int getStackCapcity() {
        return STACK_CAPCITY;
    }
}
