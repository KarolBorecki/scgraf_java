package data_structures.queue;

//TODO implemented fifo head out of bounds
public class Fifo extends Queue<Object> {
    public Fifo(int size){
        super(size);
    }

    @Override
    public Object pop() throws IndexOutOfBoundsException{
        return getAtIndex(head++);
    }
}