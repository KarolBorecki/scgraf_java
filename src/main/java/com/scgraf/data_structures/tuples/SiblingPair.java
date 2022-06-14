package com.scgraf.data_structures.tuples;

public class SiblingPair<T> extends Pair<T, T>{
    private int nextGet;
    private int nextSet;

    public SiblingPair(){
        this(null, null);
    }

    public SiblingPair(T first, T second){
        super(first, second);
        nextGet = 0;
        nextSet = 0;
    }

    public T getNextGet(){
        if(nextGet++ % 2 == 0) return getFirst();
        else return getSecond();
    }

    public void setNext(T val){
        if(nextSet++ % 2 == 0) setFirst(val);
        else setSecond(val);
    }
}
