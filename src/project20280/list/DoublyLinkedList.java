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

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

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
        // TODO
        return null;
    }

    @Override
    public void add(int i, E e) {
        // TODO

        Node<E> newNode = new Node<>(e, null, null);

        Node<E> curr = head;
        int index = 0;

        if (i == 0 || head == null) {
            addFirst(e);
        }

        while (index < i - 1){
            curr = curr.next;
            index++;
        }

        // Update links in the new node
        newNode.prev = curr;
        newNode.next = curr.next;

        // Update links in the previous and next nodes
        curr.next.prev = newNode;
        curr.next = newNode;

    }

    @Override
    public E remove(int i) {
        // TODO
        return null;
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
        // TODO
        return null;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        // TODO
        return null;
    }

    @Override
    public E removeFirst() {
        // TODO
        return null;
    }

    @Override
    public E removeLast() {
        // TODO
        return null;
    }

    @Override
    public void addLast(E e) {
        // TODO

    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null, null);

        if (head == null) {
            // If the list is empty, the new node becomes both the head and tail
            head.next = newNode;
            tail.prev = newNode;
        } else {
            // If the list is not empty, update links for the new node and the current head
            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
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