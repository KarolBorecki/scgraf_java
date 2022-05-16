package structures;

public class Fifo{
    public Object[] queue;
    public int size;
    public int head = 0;
    public int tail = 0;

    public Fifo(int size){
        queue = new Object[size];
        this.size = size;
    }

    public void push(Object o) throws IndexOutOfBoundsException{
        if(tail >= size)
            throw new IndexOutOfBoundsException("The queue tail is out of it's bounds!");

        queue[tail++] = o;
    }

    public Object pop() throws IndexOutOfBoundsException{
        System.out.println("tail: " + tail + " head: " + head + " size: " + size);
        if(head >= tail || head >= size)
            throw new IndexOutOfBoundsException("The queue head is out of it's bounds!");//TODO let logger handle this
        return queue[head++];
    }
}