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

    public boolean doesPoppedContain(T obj){
        for(int i= 0; i<this.head; i++)
            if(queue[i].equals(obj))
                return true;
        return false;
    }

    public boolean doesWholeQueContain(T obj){
        for(int i= 0; i<this.queue.length && queue[i] != null; i++)
            if(queue[i].equals(obj))
                return true;
        return false;
    }

    public int howManyElements(){
        int i;
        for(i= 0; i<this.getLength(); i++){
            if(queue[i] == null)
                return i;
        }
        return i;
    }

    public int fifoHeadIndex(){
        return head;
    }
}