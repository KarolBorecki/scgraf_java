package com.scgraf.data_structures.queue;

//TODO implemented fifo head out of bounds
public class Fifo<T> extends Queue<T> {
    @SuppressWarnings("unchecked") /* T will always be extending Object */
    public Fifo(int size){
        queue = (T[]) new Object[size];
    }

    @Override
    public T pop() throws IndexOutOfBoundsException{
        return getAtIndex(head++);
    }

    public int fifoHeadIndex(){
        return head;
    }
}