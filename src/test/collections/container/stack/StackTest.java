
package test.collections.container.stack;

import collections.container.stack.Stack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;


public abstract class StackTest {
    private Stack stack;


    public abstract Class getStackClass();

    public abstract int getStackCapcity();

    @Before
    public void setStack() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (isTestNeverFullStack()) {
            var constructor = stackConstructor();
            stack = constructor.newInstance();
        } else {
            var capcity = getStackCapcity();
            var constructor = stackConstructorOfCapcity(capcity);
            stack = constructor.newInstance(capcity);
        }
    }

    @After
    public void clearStack() {
        stack = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddedItemIsNull() {
        try {
            stack.push(null);

        } catch (Exception e) {
            assertEquals(stack.size(), 0);

            throw e;
        }
    }

    @Test
    public void testAddItemToStack() {
        var item = "item";
        stack.push(item);

        assertEquals(1, stack.size());
        assertEquals(item, stack.peek());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddItemToFullStack() {
        if (isTestNeverFullStack()) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < getStackCapcity() + 1; i++) {
            stack.push(i);
        }
    }

    @Test
    public void testRemoveItem() {
        var item = "item";
        stack.push(item);

        var removedItem = stack.pop();
        assertEquals(item, removedItem);
        assertEquals(0, stack.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveItemFromEmptyStack() {
        try {
            stack.pop();
        } catch (Exception e) {
            assertEquals(0, stack.size());

            throw e;
        }
    }

    private boolean isTestNeverFullStack() {
        try {
            stackConstructorOfCapcity(getStackCapcity());
        } catch (NoSuchMethodException e) {
            return true;
        }

        return false;
    }

    @Contract(pure = true)
    private @NotNull Constructor<Stack> stackConstructorOfCapcity(int capcity) throws NoSuchMethodException {
        return getStackClass().getConstructor(int.class);
    }

    @Contract(pure = true)
    private @NotNull
    Constructor<Stack> stackConstructor() throws NoSuchMethodException {
        return getStackClass().getConstructor();
    }
}
