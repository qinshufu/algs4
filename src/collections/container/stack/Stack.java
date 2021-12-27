package collections.container.stack;

public interface Stack {
    void push(Object item);
    Object pop();
    Object peek();
    boolean isEmpty();
    boolean isFull();
    int size();
}
