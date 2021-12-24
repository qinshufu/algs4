package collections;

//循环链表
public class CircularList<T> {
    private final class Node {
        T value;
        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private int listSize;

    CircularList() {
        head = new Node(null, null);
        head.next = head;
        listSize = 0;
    }

    public void add(T value) {
        checkValue(value);

        var node = new Node(value, head);
        if (isEmpty()) {
            head = node;
            node.next = head;

        } else {
            head.next = node;

        }
        listSize++;
    }

    public T remove() throws EmptyLinkedListError {
        var removedValue = head.value;

        if (isEmpty()) {
            throw new EmptyLinkedListError();

        } else if (listSize == 1) {
            head.value = null;

        } else {
            var prevNode = head;
            while (prevNode.next.next != null) {
                prevNode = prevNode.next;
            }

            removedValue = prevNode.next.value;
            prevNode.next = head;

        }

        listSize--;
        return removedValue;
    }

    public T removeAt(int index) {
        checkRemovedIndex(index);

        var removedValue = head.value;

        if (index == 0) {
            if (listSize == 0) {
                head.value = null;

            } else {
                var node = head;
                while (node.next != head) {
                    node = node.next;
                }

                node.next = head.next;
                head = head.next;
            }

        } else {
            var curIndex = 0;
            var curNode = head;

            while (curIndex < index - 1 && curNode.next != head) {
                curNode = curNode.next;
                curIndex++;
            }

            if (curIndex == index - 1) {
                removedValue = curNode.next.value;
                curNode.next = curNode.next.next;

            } else {
                throw new IndexOutOfBoundsException();

            }
        }

        listSize--;
        return removedValue;
    }

    public void removeValue(T value) throws EmptyLinkedListError {
        checkValue(value);

        if (isEmpty()) {
            throw new EmptyLinkedListError();
        }

        if (value.equals(head.value)) { // 是否为首节点
            if (listSize == 1) {
                head.value = null;

            } else {
                var lastNode = findLastNode();
                head = head.next;
                lastNode.next = head;

            }

        } else {
            var prevNode = head;
            var isFound = false;
            while (true) {
                if (value.equals(prevNode.next.value)) {
                    isFound = true;
                    break;
                }

                if (prevNode.next == head) {
                    break;
                }
            }

            if (!isFound) {
                return;
            }

            prevNode.next = prevNode.next.next;

        }
        listSize--;
    }

    public void insertAt(int index, T value) {
        checkInsertIndex(index);
        checkValue(value);

        var node = new Node(value, null);
        if (index == 0) {
            if (listSize == 0) {
                head.value = value;

            } else {
                var lastNode = head;
                try {
                    lastNode = findLastNode();

                } catch (EmptyLinkedListError e) {
                    // 这永远不会发生
                    e.printStackTrace();
                    System.exit(-1);
                }

                node.next = head;
                lastNode.next = node;
                head = node;
            }

        } else {
            var prevNode = head;
            var prevIndex = 0;

            while (prevIndex < index - 1 && prevNode.next != head) {
                prevNode = prevNode.next;
                prevIndex++;
            }

            if (prevIndex == index - 1) {
                node.next = prevNode.next;
                prevNode.next = node;
            }

        }

        listSize++;
    }

    public int size() {
        return listSize;
    }

    public boolean isEmpty() {
        return listSize == 0;
    }

    private void checkValue(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > listSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkRemovedIndex(int index) {
        if (index < 0 || index > listSize - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node findLastNode() throws EmptyLinkedListError {
        var node = head;
        while (node.next != head) {
            node = node.next;
        }

        if (node.value == null) {
            throw new EmptyLinkedListError();
        }

        return node;
    }
}

class EmptyLinkedListError extends Exception {
}