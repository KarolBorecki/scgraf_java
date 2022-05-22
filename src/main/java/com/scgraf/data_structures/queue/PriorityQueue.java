package com.scgraf.data_structures.queue;

import com.scgraf.utils.Utils;

public class PriorityQueue<T extends Comparable<T>> extends Queue<T>{
    @SuppressWarnings("unchecked")
    public PriorityQueue(int size){
        queue = (T[]) new Comparable[size];
    }

    @Override
    public T pop() throws IndexOutOfBoundsException {
        int greatest = head;
        for(int i=head+1; i<tail; i++)
            if (getAtIndex(greatest).compareTo(getAtIndex(i)) < 0)
                greatest = i;

        Utils.Swap(queue, greatest, head);
        return getAtIndex(head++);
    }
}
