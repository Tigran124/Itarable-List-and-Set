import java.util.Iterator;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedList(T element) {
        head = new Node<>();
        tail = head;
        head.element = element;
    }

    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            private int curentSize = 0;
            @Override
            public boolean hasNext() {
                return curentSize < size;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    curentSize ++;
                    return getAt(curentSize --);
                }
                return null;
            }
        };
        return iterator;
    }


    public LinkedList() {
    }

    public T getHead() {
        return head.element;
    }

    public T getAt(int index) {
        Node<T> temp = head;
        if (size <= index) {
            throw new ArrayIndexOutOfBoundsException("YOU Are out of Bound");
        }
        for (int i = 0; i < index; i++) {
            temp = temp.nextNode;
        }
        return temp.element;
    }

    public void addAtHead(T element) {
        Node<T> tempNode = new Node<>();
        tempNode.element = element;
        tempNode.nextNode = head;
        if (head == null) {
            head = tempNode;
            tail = head;
            size++;
            return;
        }
        head.previusNode = tempNode;
        this.head = tempNode;
        size++;
    }

    public void addAt(int index, T element) {
        if (size < index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("YOU Are out of Bound");
        }
        if (size == index) {
            addAtTail(element);
        }
        if (index == 0) {
            addAtHead(element);
        } else {
            Node<T> temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.nextNode;
            }
            Node<T> nodeToAdd = new Node<>();
            nodeToAdd.nextNode = temp.nextNode;
            nodeToAdd.previusNode = temp;
            temp.nextNode.previusNode = nodeToAdd;
            temp.nextNode = nodeToAdd;
            nodeToAdd.element = element;
            size++;
        }
    }


    public void addAtTail(T element) {
        Node<T> nodeToAdd = new Node<>();
        nodeToAdd.element = element;
        if (head == null) {
            head = nodeToAdd;
            tail = head;
            size++;
            return;
        }
        tail.nextNode = nodeToAdd;
        nodeToAdd.previusNode = tail;
        tail = nodeToAdd;
        size++;
    }

    public T removeHead() {
        T removedElement = head.element;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return removedElement;
        }
        head.nextNode.previusNode = null;
        head = head.nextNode;
        size--;
        return removedElement;
    }

    public T removeTail() {
        T removedElement = tail.element;
        tail.previusNode.nextNode = null;
        tail = tail.previusNode;
        size--;
        return removedElement;
    }

    public T getTail() {
        return tail.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}