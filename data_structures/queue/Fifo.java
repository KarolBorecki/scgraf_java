package data_structures.queue;

import data_structures.graph.Node;

//TODO implemented fifo head out of bounds
public class Fifo<T extends Node> extends Queue<T> {
    @SuppressWarnings("unchecked")
    public Fifo(int size){
        queue = (T[]) new Node[size];
    }

    @Override
    public T pop() throws IndexOutOfBoundsException{
        return getAtIndex(head++);
    }
}