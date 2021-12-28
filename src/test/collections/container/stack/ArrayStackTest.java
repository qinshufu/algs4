package test.collections.container.stack;

import collections.container.stack.ArrayStack;


public class ArrayStackTest extends StackTest {
    private static final int STACK_CAPCITY = 100;
    private static final Class<ArrayStack> STACK_CLASS = ArrayStack.class;

    @Override
    public Class getStackClass() {
        return STACK_CLASS;
    }

    @Override
    public int getStackCapcity() {
        return STACK_CAPCITY;
    }
}
