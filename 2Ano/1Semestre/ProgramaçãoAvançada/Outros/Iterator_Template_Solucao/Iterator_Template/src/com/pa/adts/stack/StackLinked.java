package com.pa.adts.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackLinked<E> implements Stack<E> {
    private Node top;
    private int size;

    public StackLinked() {
        size=0;
        top=null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top==null;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.elem;
    }

    @Override
    public void push(E elem) throws FullStackException {
         Node newNode= new Node(elem,top);
         top=newNode;
         size++;
    }

    @Override
    public E pop() throws EmptyStackException {
        E elem = peek();
        top=top.next;
        size--;
        return elem;
    }

    @Override
    public Iterator<E> iteratorReversed() {
        return new MyIteratorReversed();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIteratorReversed implements Iterator<E> {
        private Node cursor;

        public MyIteratorReversed() {
            if(isEmpty())
                this.cursor = null;
            else {
                this.cursor = top;
                while(cursor.next != null ) {
                    this.cursor = cursor.next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException("Iterator depleted.");

            E nextElement = cursor.elem;

            if(cursor == top) {
                cursor = null;
            } else {
                Node atual = top;
                while(atual.next != cursor) {
                    atual = atual.next;
                }
                cursor = atual;
            }

            return nextElement;
        }
    }

    private class MyIterator implements Iterator<E> {
        private Node cursor;

        public MyIterator() {
            this.cursor = top;
        }

        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException("Iterator depleted.");

            E nextElement = cursor.elem;
            cursor = cursor.next;
            return nextElement;
        }
    }

    private class Node{
        private E elem;
        private Node next;

        public Node(E elem) {
            this(elem,null);
        }

        public Node(E elem, Node next) {
            this.elem = elem;
            this.next = next;
        }


    }

}
