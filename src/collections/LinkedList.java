package collections;

class EmptyLinkedListException extends Exception {

}

class ValueNotInLinkedListError extends Exception {

}

class NullIsNotAllowedError extends Exception {
}

// 单头链表
public class LinkedList<T> {
    private Node head; // 提供空节点能够简化判断逻辑

    private final class Node {
        T value;
        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    LinkedList() {
        head = null;
    }

    public void add(T value) throws NullIsNotAllowedError {
        throwErrorIfValueIsNull(value);

        var anode = new Node(value, null);
        if (isEmpty()) {
            head = anode;

        } else {
            var preNode = head;
            while (preNode.next != null) {
                preNode = preNode.next;
            }

            preNode.next = anode;
        }
    }

    public void del(T value) throws ValueNotInLinkedListError, NullIsNotAllowedError {
        throwErrorIfValueIsNull(value);

        if (isEmpty()) {
            throw new ValueNotInLinkedListError();
        }

        if (head.value.equals(value)) {
            head = head.next;
            return;
        }

        var preNode = head;
        while (preNode.next != null) {
            if (preNode.next.value.equals(value)) {
                preNode.next = preNode.next.next;
                return;
            }
        }

        throw new ValueNotInLinkedListError();
    }

    public T delAt(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            var removedValue = head.value;
            head = head.next;
            return removedValue;
        }

        var preNode = head;
        var nextIndex = 1;
        while (index > nextIndex && preNode.next != null) {
            preNode.next = preNode.next.next;
        }

        if (index > nextIndex) {
            throw new IndexOutOfBoundsException();
        }

        var removeValue = preNode.next.value;
        preNode.next = preNode.next.next;
        return removeValue;
    }

    public void insertAt(int index, T value) throws NullIsNotAllowedError {
        throwErrorIfValueIsNull(value);

        var aNode = new Node(value, null);
        if (index == 0) {
            aNode.next = head;
            head = aNode;
            return;
        }

        var preNode = head;
        var nextIndex = 1;
        while (preNode.next != null && nextIndex < index) {
            preNode = preNode.next;
            nextIndex++;
        }

        if (nextIndex < index) {
            throw new IndexOutOfBoundsException();
        }

        preNode.next = new Node(value, preNode.next);
    }

    public T at(int index) throws EmptyLinkedListException {
        if (isEmpty()) {
            throw new EmptyLinkedListException();
        }

        if (index == 0) {
            return head.value;
        }

        var node = head;
        var nowIndex = 0;
        while (node.next != null && index > nowIndex) {
            node = node.next;
        }

        if (index > nowIndex) {
            throw new IndexOutOfBoundsException();
        }

        return node.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void throwErrorIfValueIsNull(T value) throws NullIsNotAllowedError {
        if (value == null) {
            throw new NullIsNotAllowedError();
        }
    }

    public static void main(String... args) throws NullIsNotAllowedError, EmptyLinkedListException {
        var lk = new LinkedList<Integer>();
        for (var i = 0; i < 1000; i++) {
            lk.add(i);
        }

        for (var i = 0; i < 1000; i++) {
            assert lk.at(i) == i;
        }

        for (var i = 0; i < 1000; i++) {
            var deletedvalue = lk.delAt(i);
            assert deletedvalue == i;

            lk.insertAt(i, i);
        }


        System.out.println("PASS");
    }

}
