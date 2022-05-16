package data_structures.queue;

public class PriorityQueue<T extends Comparable<T>> extends Queue<T>{
    @Override
    public T pop() throws IndexOutOfBoundsException {
        T greatest = getAtIndex(0);
        for (T o : queue)
            if (greatest.compareTo(o) > 0)
                greatest = o;

        return greatest;
    }
}
