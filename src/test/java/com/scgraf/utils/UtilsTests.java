package com.scgraf.utils;

import org.junit.Assert;

public class UtilsTests {

    @org.junit.Test
    public void testSwap(){
        final Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Utils.swap(arr, 0, 2);
        Assert.assertEquals(2, arr[0].intValue());
        Assert.assertEquals(0, arr[2].intValue());
        Utils.swap(arr, 0, 2);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(2, arr[2].intValue());

        Utils.swap(arr, 5, 8);
        Assert.assertEquals(8, arr[5].intValue());
        Assert.assertEquals(5, arr[8].intValue());
        Utils.swap(arr, 8, 5);
        Assert.assertEquals(5, arr[5].intValue());
        Assert.assertEquals(8, arr[8].intValue());

        Utils.swap(arr, 10, 5);
        Assert.assertEquals(5, arr[5].intValue());

        Utils.swap(arr, -1, 5);
        Assert.assertEquals(5, arr[5].intValue());
    }

    @org.junit.Test
    public void testResizeArray(){
        Integer[] arr = {0, 1, 2, 3, 4};

        arr = Utils.resizeArray(arr, 2);
        Assert.assertEquals(10, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);

        arr = Utils.resizeArray(arr, 3);
        Assert.assertEquals(30, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);

        arr = Utils.resizeArray(arr, -1);
        Assert.assertEquals(30, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);
    }

    @org.junit.Test
    public void testResizeArrayTo(){
        Integer[] arr = {0, 1, 2, 3, 4};

        arr = Utils.resizeArrayTo(arr, 8);
        Assert.assertEquals(8, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);

        arr = Utils.resizeArrayTo(arr, 10);
        Assert.assertEquals(10, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);

        arr = Utils.resizeArrayTo(arr, -1);
        Assert.assertEquals(10, arr.length);
        Assert.assertEquals(0, arr[0].intValue());
        Assert.assertEquals(1, arr[1].intValue());
        Assert.assertEquals(2, arr[2].intValue());
        Assert.assertEquals(3, arr[3].intValue());
        Assert.assertEquals(4, arr[4].intValue());
        Assert.assertNull(arr[5]);
    }

    @org.junit.Test
    public void testArrContains(){
        Integer[] arr = {0, 1, 2, 3, 4};

        Assert.assertTrue(Utils.arrContains(arr, 4,0, 4));
        Assert.assertTrue(Utils.arrContains(arr, 0,0, 4));
        Assert.assertTrue(Utils.arrContains(arr, 3,3, 4));
        Assert.assertTrue(Utils.arrContains(arr, 4,4, 4));
        Assert.assertTrue(Utils.arrContains(arr, 1,0, 2));
        Assert.assertFalse(Utils.arrContains(arr, 0,-1, 4));
        Assert.assertFalse(Utils.arrContains(arr, 5,0, 4));
    }
}
