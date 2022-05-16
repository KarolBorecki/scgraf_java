package structures;

//TODO implemented fifo head out of bounds
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
        return getAtIndex(head++);
    }

    public Object peek() throws IndexOutOfBoundsException{
        return getAtIndex(head);
    }

    public Object getAtIndex(int index) throws IndexOutOfBoundsException{
        if(index >= tail || index >= queue.length)
            throw new IndexOutOfBoundsException("The requested index is out of queue bounds!");//TODO let logger handle this
        return queue[index];
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