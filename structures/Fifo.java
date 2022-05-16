package structures;

public class Fifo{
    private Object[] queue;
    private int head = 0;
    private int tail = 0;

    private static final int RESIZE_FACTOR = 2;

    public Fifo(int size){
        queue = new Object[size];
    }

    public void push(Object o) throws IndexOutOfBoundsException{
        if(tail >= queue.length)
            resizeQueue(RESIZE_FACTOR);
        queue[tail++] = o;
    }

    public Object pop() throws IndexOutOfBoundsException{
        if(head >= tail || head >= queue.length)
            throw new IndexOutOfBoundsException("The queue head is out of it's bounds!");//TODO let logger handle this
        return queue[head++];
    }

    public Object peek() throws IndexOutOfBoundsException{
        if(head > tail)
            throw new IndexOutOfBoundsException("The queue head is out of it's bounds!");
        return queue[head];
    }

    public boolean IsEmpty(){
        return head == tail;
    }

    private void resizeQueue(int resizeFactor){
        Object[] newQueue = new Object[resizeFactor * queue.length];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }
}