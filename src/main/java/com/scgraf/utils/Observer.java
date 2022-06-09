package com.scgraf.utils;

public interface Observer<T> {
    void call(T param);
}