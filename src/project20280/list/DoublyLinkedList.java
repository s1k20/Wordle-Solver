package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
           data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }
    Node<E> head;
    Node<E> tail;
    int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
        Node<E> newNode = new Node<E>(e, null, null);

        newNode.next = succ;
        newNode.prev = pred;
        pred.next = newNode;
        succ.prev = newNode;
        size++;

    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Node<E> current = head.next;
        for (int index = 0; index < i; index++) {
            current = current.next;
        }

        return current.data;
    }

        @Override
    public void add(int i, E e) {
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
            }

            Node<E> newNode = new Node<>(e, null, null);
            Node<E> current = head;

            for (int index = 0; index < i; index++) {
                current = current.next;
            }

            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;

            size++;
        }

    @Override
    public E remove(int i) {
        // TODO
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }

        Node<E> current = head;
        for (int index = 0; index < i; index++) {
            current = current.next;
        }

        Node<E> removedNode = current.next;
        current.next = removedNode.next;
        removedNode.next.prev = current;
        size--;

        return removedNode.data;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        if (n == null || n == head || n == tail) {
            // Cannot remove head, tail, or a null node
            return null;
        }

        // Update the pointers of the neighboring nodes to bypass the node to be removed
        n.prev.next = n.next;
        n.next.prev = n.prev;

        size--;

        return n.data;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.prev.getData();
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        Node<E> removedNode = head.next;
        head.next = removedNode.next;
        removedNode.next.prev = head;
        size--;

        return removedNode.data;
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        }

        Node<E> removedNode = tail.prev;
        tail.prev = removedNode.prev;
        removedNode.prev.next = tail;
        size--;

        return removedNode.data;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null, null);

        if (isEmpty()) {
            // If the list is empty, set both head and tail to the new node
            head.next = newNode;
            tail.prev = newNode;
            size++;
        } else {
            // Connect the new node to the current tail
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;
            size++;
        }


    }


    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null, null);

        if (isEmpty()) {
            // If the list is empty, the new node becomes both the head and tail
            head.next = newNode;
            tail.prev = newNode;
            newNode.next = tail;
            newNode.prev = head;
        } else {
            // If the list is not empty, update links for the new node and the current head
            newNode.next = head.next;
            newNode.prev = head;
            Node<E> curr = head.next;
            curr.prev = newNode;
            head.next = newNode;
        }

        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(5);
//        ll.addFirst(3);
        ll.addLast(6);
//        ll.addLast(6);
//        ll.addLast(6);
//        ll.addFirst(1);
//        ll.addFirst(2);
//        ll.addLast(-1);
//        ll.add(0, -1);
//        ll.add(1, 2);
//        ll.add(2, 5);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}