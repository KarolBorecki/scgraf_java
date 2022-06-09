package com.scgraf.data_structures.queue;

public interface IQueue<T> {
    int RESIZE_FACTOR = 2;

    void push(T o) throws IndexOutOfBoundsException;

    T pop() throws IndexOutOfBoundsException;

    T peek() throws IndexOutOfBoundsException;

    T getAtIndex(int index) throws IndexOutOfBoundsException;

    boolean isEmpty();

    void resizeQueue(int resizeFactor);

    @Override
    String toString();
}
