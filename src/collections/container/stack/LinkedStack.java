package collections.container.stack;

import collections.container.AbstractContainer;

public class LinkedStack extends AbstractContainer implements Stack {
    private static final class Node {
        Object value;
        Node prev;
        Node next;

        Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private Node linkedlist;
    private Node lastNode;
    private int listsize;

    @Override
    public void push(Object item) {
        checkNull(item);

        var node = new Node(item, null, null);
        if (isEmpty()) {
            linkedlist = node;
            lastNode = node;

        } else {
            node.prev = lastNode;
            lastNode.next = node;
            lastNode = node;
        }

        listsize++;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: Stack is empty.");
        }

        var removedValue = lastNode.value;
        if (hasOnlyOne()) {
            lastNode = linkedlist = null;

        } else {
            lastNode = lastNode.prev;
            lastNode.next = null;
        }

        listsize--;
        return removedValue;
    }

    private boolean hasOnlyOne() {
        return linkedlist == lastNode;
    }

    @Override
    public Object peek() {
        return lastNode.value;
    }

    @Override
    public boolean isEmpty() {
        return listsize == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return listsize;
    }

    private void checkNull(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Error: Value is null.");
        }
    }
}