package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            data = e;
            next = n;
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        Node<E> current = tail.getNext();

        for(int j = 0;j<i;j++){
            current = current.getNext();
        }

        return current.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        Node<E> current = tail;
        for(int j = 0;j<i;j++){
            current = current.getNext();
        }
        Node<E> newNode = new Node<>(e,current.getNext());
        current.setNext(newNode);
    }

    @Override
    public E remove(int i) {
        // TODO
        if(i == 0){
            return removeFirst();
        }
        else if(i == size-1){
            return removeLast();
        }else{
            Node<E> current = tail.getNext();
            for(int j = 0 ;j <i-1;j++){
                current = current.getNext();
            }
            E removed = current.getNext().getData();
            current.setNext(current.getNext().getNext());//bypasses the removed item
            size--;
            return removed;

        }
    }

    public void rotate() {
        // TODO
        if (tail != null) // if empty, do nothing
            tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

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
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if(isEmpty()){
            return null;
        }else{
            E removed = tail.getNext().getData();
            if(tail == tail.getNext()){//list only has one element
                tail = null;
            }else{
                tail.setNext(tail.getNext().getNext());//bypass the first element
            }
            size--;

            return removed;
        }

    }

    @Override
    public E removeLast() {
        // TODO
        if(isEmpty()){
            return null;
        }else if(tail == tail.getNext()){
            E toRemove = tail.getData();
            tail = null;
            size--;
            return toRemove;
        }
        else{
            Node<E> current = tail;
            for(int i = 0;i<size-1;i++){
                current = current.getNext();
            }
            E toRemove = current.getNext().getData();
            current.setNext(current.getNext().getNext());//bypassing the node i want removed
            tail = current;//update tail as it would have been removed otherwise
            size--;
            return toRemove;
        }

    }

    @Override
    public void addFirst(E e) {
        // TODO
        if(isEmpty()){
            tail = new Node<>(e,null);//creates new tail with it pointing null
            tail.setNext(tail);
        }
        else{
            Node<E> newNode = new Node<>(e,tail.getNext());//creates new node with its next being the prevoius tails next
            tail.setNext(newNode);//sets the tails next to be the newNode( new "Head")
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);       // Insert new element at the front of the list
        tail = tail.getNext(); // Now the new element becomes the tail
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}