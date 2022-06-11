package com.scgraf.data_structures;

import com.scgraf.data_structures.queue.PriorityQueue;
import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTests {
    @Test
    public void testPop(){
        PriorityQueue<Integer> queue = new PriorityQueue<>(10);
        queue.push(-200);
        queue.push(10);
        queue.push(12);
        queue.push(143);
        queue.push(113123);
        queue.push(-100);

        Assert.assertEquals(113123, queue.pop().intValue());
        Assert.assertEquals(143, queue.pop().intValue());
        Assert.assertEquals(12, queue.pop().intValue());
        Assert.assertEquals(10, queue.pop().intValue());
        Assert.assertEquals(-100, queue.pop().intValue());
        Assert.assertEquals(-200, queue.pop().intValue());
    }
}
