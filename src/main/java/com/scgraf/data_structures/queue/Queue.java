package com.scgraf.data_structures.queue;

import java.util.Arrays;

public abstract class Queue<T> implements IQueue<T>{
    protected T[] queue;

    protected int head = 0;
    protected int tail = 0;

    @Override
    public void push(T o) throws IndexOutOfBoundsException{
        if(tail >= queue.length)
            resizeQueue(RESIZE_FACTOR);
        queue[tail++] = o;
    }

    @Override
    public T peek() throws IndexOutOfBoundsException{
        return getAtIndex(head);
    }

    @Override
    public T getAtIndex(int index) throws IndexOutOfBoundsException{
        if(index >= tail || index >= queue.length)
            throw new IndexOutOfBoundsException("The requested index is out of queue bounds!");//TODO let logger handle this
        return queue[index];
    }

    @Override
    public boolean IsEmpty(){
        return head == tail;
    }

    @Override
    public void resizeQueue(int resizeFactor){
        queue = java.util.Arrays.copyOf(queue, queue.length * RESIZE_FACTOR);
    }

    @Override
    public void swapElements(int index1, int index2) throws IndexOutOfBoundsException{
        T temp = this.getAtIndex(index1);
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }

    public int getLength(){
        return queue.length;
    }

    @Override
    public String toString() {
        return "QUEUE:" +
                "\n   queue = " + Arrays.toString(queue) +
                "\n   head =  " + head +
                "\n   tail =  " + tail;
    }

}
