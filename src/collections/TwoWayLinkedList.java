package collections;

class EmptyTwoWayLinkedList extends Exception {
}

public class TwoWayLinkedList<T> {
    class Node {
        T value;
        Node prev;
        Node next;

        Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T value) {
        var node = new Node(value, null, null);
        if (size == 0) {
            head = tail = node;

        } else if (size == 1) {
            node.prev = head;
            head.next = node;
            tail = node;

        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }

        size++;
    }

    public T del() throws EmptyTwoWayLinkedList {
        if (size == 0) {
            throw new EmptyTwoWayLinkedList();

        }

        T removedValue = tail.value;
        if (size == 1) {
            head = tail = null;

        } else {
            var newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
        }

        size--;
        return removedValue;
    }

    public void insertAt(int index, T value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        var node = new Node(value, null, null);
        if (size == 0) {
            head = tail = node;

        } else if (size == 1) {
            node.prev = head;
            head.next = node;
            tail = node;

        } else {
            var curIndex = 0;
            var curNode = head;
            while (curNode != null && curIndex < index) {
                curNode = curNode.next;
                curIndex++;
            }

            if (curNode == null) {
                throw new IndexOutOfBoundsException();
            }

            if (curNode.prev == null) {
                node.next = head;
                head.prev = node;
                head = node;

            } else if (curNode.next == null) {
                node.prev = tail;
                tail.next = node;
                tail = node;

            } else {
                curNode.prev.next = node;
                curNode.prev = node;
                node.prev = curNode.prev;
                node.next = curNode;
            }
        }

        size++;
    }

    public T removeAt(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        T removedValue;
        if (size == 1) {
            removedValue = head.value;
            head = tail = null;

        } else {
            var curIndex = 0;
            var curNode = head;
            while (curNode != null && curIndex < index) {
                curNode = curNode.next;
                curIndex++;
            }

            if (curNode == null) {
                throw new IndexOutOfBoundsException();
            }

            removedValue = curNode.value;
            if (curNode.prev == null) {
                head = curNode.next;
                head.prev = null;

            } else if (curNode.next == null) {
                tail = tail.prev;
                tail.next = null;

            } else {
                curNode.prev.next = curNode.next;
                curNode.next.prev = curNode.prev;
            }
        }

        return removedValue;
    }

    public int length() {
        return size;
    }
}
