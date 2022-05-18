package data_structures.tuples;

public abstract class Pair<T, E> implements ITuple{
    public T first;
    public E second;

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }
}
