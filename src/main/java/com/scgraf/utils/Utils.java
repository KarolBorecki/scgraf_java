package com.scgraf.utils;

public class Utils {
    public static void swap(Object[] arr, int a, int b) {
        Object c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static <T> T[] resizeArray(T[] arr, int resizeFactor) {
        return java.util.Arrays.copyOf(arr, arr.length * resizeFactor);
    }

    public static <T> T[] resizeArrayTo(T[] arr, int Size) {
        return java.util.Arrays.copyOf(arr, Size);
    }

    public static <T> boolean arrContains(T[] arr, T wanted, int start, int end) {
        for (int i = start; i < end; i++)
            if (arr[i] == wanted) return true;
        return false;
    }
}
