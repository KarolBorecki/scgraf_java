package com.scgraf.data_structures.queue;

import com.scgraf.utils.Utils;

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

    public boolean containsInPopped(T obj){
        return Utils.arrContains(queue, obj, 0, head);
    }

    public boolean containsInFilledElements(T obj){
        return Utils.arrContains(queue, obj, 0, queue.length);
    }

    public int queueFilledSize(){
        return tail + 1;
    }

    public int queueSize(){
        return head-tail;
    }

    @Override
    public boolean isEmpty(){
        return head == tail;
    }

    @Override
    public void resizeQueue(int resizeFactor){
        queue = Utils.resizeArray(queue, resizeFactor);
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
