package collections;

public class Stack<T> {
    private T[] items;
    private int index;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        items = (T[]) new Object[size];
        index = 0;
    }

    public boolean isFull() {
        return index + 1 >= items.length;
    }

    public boolean isEmpty() {
        return index <= 0;
    }

    public int size() {
        return index;
    }

    public void push(T item) throws FullStackExceptiion {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isFull()) {
            throw new FullStackExceptiion();
        }

        items[index++] = item;
    }

    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return items[index - 1];
    }

    public T pop() throws EmptyStackException {
        if (index < 1) {
            throw new EmptyStackException();
        }

        return items[--index];
    }
}
