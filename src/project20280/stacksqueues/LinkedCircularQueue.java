package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.CircularlyLinkedList;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        // TODO Auto-generated method stub
        list.addFirst(e);
    }

    @Override
    public E first() {
        if(isEmpty()){
            return null;
        }
        return list.first();
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null; // Return null if the queue is empty
        return list.removeFirst();
    }

}
