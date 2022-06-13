package com.scgraf.utils;

public class Utils {
    public static void swap(Object[] arr, int a, int b) {
        if (a < 0 || b < 0 || a > arr.length - 1 || b > arr.length - 1) return;

        Object c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public static <T> T[] resizeArray(T[] arr, int resizeFactor) {
        if (resizeFactor < 2) return arr;
        return java.util.Arrays.copyOf(arr, arr.length * resizeFactor);
    }

    public static <T> T[] resizeArrayTo(T[] arr, int size) {
        if (size < 0) return arr;
        return java.util.Arrays.copyOf(arr, size);
    }

    public static <T> T[] resizeArrayBy(T[] arr, int size) {
        if (size <= 0) return arr;
        return java.util.Arrays.copyOf(arr, arr.length + size);
    }

    public static <T> boolean arrContains(T[] arr, T wanted, int start, int end) {
        if (start < 0 || start >= arr.length || end < 0 || end >= arr.length) return false;
        for (int i = start; i <= end; i++)
            if (arr[i] == wanted) return true;
        return false;
    }
}
