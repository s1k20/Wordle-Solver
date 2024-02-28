package project20280.stacksqueues;

import project20280.interfaces.Stack;

import project20280.list.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    SinglyLinkedList<E> ll = new SinglyLinkedList<>();

    public static void main(String[] args) {
    }

    public LinkedStack() {
        // TODO

    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        // TODO
        ll.addFirst(e);
    }

    @Override
    public E top() {
        // TODO
        return ll.first();
    }

    @Override
    public E pop() {
       return ll.removeFirst();
    }

    public static <E> void Reverse(E[] a){
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (int i=0; i < a.length; i++){
            buffer.push(a[i]);
        }
        for (int i=0; i < a.length; i++){
            a[i] = buffer.pop();
        }
    }
    public String toString() {
        return ll.toString();
    }
}
