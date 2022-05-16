package data_structures;

//TODO implemented fifo head out of bounds
public class Fifo extends Queue {
    public Fifo(int size){
        super(size);
    }

    @Override
    public Object pop() throws IndexOutOfBoundsException{
        return getAtIndex(head++);
    }
}