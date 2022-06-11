package com.scgraf.data_structures;

import com.scgraf.data_structures.queue.Fifo;
import org.junit.Assert;
import org.junit.Test;

public class FifoTests {
    @Test
    public void testPush() {
        Fifo<Integer> fifo = new Fifo<>(5);

        fifo.push(0);
        Assert.assertEquals(0, fifo.getAtIndex(0).intValue());
        fifo.push(1);
        Assert.assertEquals(1, fifo.getAtIndex(1).intValue());
        fifo.push(2);
        Assert.assertEquals(2, fifo.getAtIndex(2).intValue());
    }

    @Test
    public void testPop() {
        Fifo<Integer> fifo = new Fifo<>(5);

        fifo.push(0);
        fifo.push(1);
        fifo.push(2);

        Assert.assertEquals(0, fifo.pop().intValue());
        Assert.assertEquals(2, fifo.queueSize());
        Assert.assertEquals(1, fifo.pop().intValue());
        Assert.assertEquals(1, fifo.queueSize());
        Assert.assertEquals(2, fifo.pop().intValue());
        Assert.assertEquals(0, fifo.queueSize());
    }

    @Test
    public void testGetAtIndex() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 5; i++)
            fifo.pop();

        for (int i = 0; i < 10; i++)
            Assert.assertEquals(i, fifo.getAtIndex(i).intValue());
    }

    @Test
    public void testContainsPopped() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 5; i++)
            fifo.pop();

        for (int i = 0; i < 5; i++)
            Assert.assertTrue(fifo.containsInPopped(i));
        for (int i = 5; i < 10; i++)
            Assert.assertFalse(fifo.containsInPopped(i));
    }

    @Test
    public void testContainsInFilledElements() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 5; i++)
            fifo.pop();

        for (int i = 0; i < 10; i++)
            Assert.assertTrue(fifo.containsInFilledElements(i));

    }

    @Test
    public void testFilledSize() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 5; i++)
            fifo.pop();

        Assert.assertEquals(10, fifo.queueFilledSize());
    }

    @Test
    public void testSize() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 5; i++)
            fifo.pop();

        Assert.assertEquals(5, fifo.queueSize());
    }

    @Test
    public void testIsEmpty() {
        Fifo<Integer> fifo = new Fifo<>(10);

        for (int i = 0; i < 10; i++)
            fifo.push(i);

        for (int i = 0; i < 10; i++)
            fifo.pop();

        Assert.assertTrue(fifo.isEmpty());
    }
}
