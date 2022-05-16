package data_structures.queue;

import java.util.Collections;

public class PriorityQueue<T extends Comparable<T>> extends Queue<T>{
    @SuppressWarnings("unchecked")
    public PriorityQueue(int size){
        queue = (T[]) new Comparable[size];
    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        int greatest = head;
        for(int i=head+1; i<tail; i++)
            if (getAtIndex(i).compareTo(getAtIndex(i)) > 0)
                greatest = i;
        swap()
        return greatest;
    }
}
