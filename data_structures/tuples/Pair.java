package data_structures.tuples;

public abstract class Pair<T, E> implements ITuple{
    public final T first;
    public final E second;

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }
}
