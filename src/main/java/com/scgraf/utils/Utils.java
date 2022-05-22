package com.scgraf.utils;

public class Utils {
    public static void Swap(Object[] arr, int a, int b){
        Object c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }
}
