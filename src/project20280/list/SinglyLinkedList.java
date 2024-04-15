package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> head = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    @Override
    public void add(int position, E e) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) {
            addFirst(e);
        } else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.getNext();
            }
            Node<E> newNode = new Node<>(e, prev.getNext());
            prev.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<E> last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) {
            return removeFirst();
        } else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.getNext();
            }
            Node<E> toRemove = prev.getNext();
            prev.setNext(toRemove.getNext());
            size--;
            return toRemove.getElement();
        }
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        E result = head.getElement();
        head = head.getNext();
        size--;
        return result;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        } else {
            Node<E> current = head;
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }
            E result = current.getNext().getElement();
            current.setNext(null);
            size--;
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E result = current.getElement();
                current = current.getNext();
                return result;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        for (Integer i : ll) {
            System.out.println(i);
        }
    }
}
