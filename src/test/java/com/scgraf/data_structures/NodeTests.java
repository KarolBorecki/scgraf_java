package com.scgraf.data_structures;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import org.junit.Assert;
import org.junit.Test;

public class NodeTests {

    @Test
    public void testConstructor() {
        Graph g1 = new Graph();

        Node node1 = new Node(g1);
        Assert.assertEquals(0, node1.getID());
        Assert.assertEquals(0, node1.getGraphID());

        Node node2 = new Node(g1);
        Assert.assertEquals(1, node2.getID());
        Assert.assertEquals(1, node2.getGraphID());

        Node node3 = new Node(g1);
        Assert.assertEquals(2, node3.getID());
        Assert.assertEquals(2, node3.getGraphID());

        Node node4 = new Node(g1);
        Assert.assertEquals(3, node4.getID());
        Assert.assertEquals(3, node4.getGraphID());

        Node node5 = new Node(g1);
        Assert.assertEquals(4, node5.getID());
        Assert.assertEquals(4, node5.getGraphID());

        Graph g2 = new Graph();

        Node node6 = new Node(g2);
        Assert.assertEquals(5, node6.getID());
        Assert.assertEquals(0, node6.getGraphID());

        Node node7 = new Node(g2);
        Assert.assertEquals(6, node7.getID());
        Assert.assertEquals(1, node7.getGraphID());

        Node node8 = new Node(g2);
        Assert.assertEquals(7, node8.getID());
        Assert.assertEquals(2, node8.getGraphID());

        Node node9 = new Node(g2);
        Assert.assertEquals(8, node9.getID());
        Assert.assertEquals(3, node9.getGraphID());

        Node node10 = new Node(g2);
        Assert.assertEquals(9, node10.getID());
        Assert.assertEquals(4, node10.getGraphID());
    }
}
