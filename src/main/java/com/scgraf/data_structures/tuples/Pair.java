package com.scgraf.data_structures.tuples;

public class Pair<T, E> implements ITuple {
    public T first;
    public E second;

    public Pair(){
        this(null, null);
    }

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    public void setPair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public boolean isFirstNotNull(){return first != null;}
    public boolean isSecondNotNull(){return second != null;}

}
