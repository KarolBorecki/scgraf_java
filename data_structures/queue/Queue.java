package data_structures.queue;

import java.util.Arrays;

public abstract class Queue implements IQueue{
    protected Object[] queue;

    protected int head = 0;
    protected int tail = 0;

    public Queue(int size){
        queue = new Object[size];
    }

    @Override
    public void push(Object o) throws IndexOutOfBoundsException{
        if(tail >= queue.length)
            resizeQueue(RESIZE_FACTOR);
        queue[tail++] = o;
    }

    @Override
    public Object peek() throws IndexOutOfBoundsException{
        return getAtIndex(head);
    }

    @Override
    public Object getAtIndex(int index) throws IndexOutOfBoundsException{
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
        Object[] newQueue = new Object[resizeFactor * queue.length];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }

    @Override
    public String toString() {
        return "QUEUE:" +
                "\n   queue = " + Arrays.toString(queue) +
                "\n   head =  " + head +
                "\n   tail =  " + tail;
    }
}
