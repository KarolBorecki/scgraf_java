package com.scgraf.data_structures;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.tuples.Size;
import com.scgraf.generator.GraphGenerator;
import org.junit.Assert;
import org.junit.Test;

public class GraphTests {
    private static final int testGrapWidth = 10;
    private static final int testGrapHeight = 10;
    private static final double testGrapMaxPathWeight = 10.0;

    @Test
    public void testBuilder() {
        Graph graph = new Graph();
        Assert.assertNotNull(graph);

        graph.setSize(new Size(testGrapWidth, testGrapHeight)).setWeight(testGrapMaxPathWeight).build();
        for (int x = 0; x < testGrapWidth; x++)
            for (int y = 0; y < testGrapHeight; y++)
                Assert.assertNotNull(graph.getNode(x, y));
    }

    @Test
    public void testGetNode() {
        Graph graph = GraphGenerator.GenerateExample();
        int width = graph.getSize().width();
        int height = graph.getSize().height();

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                Assert.assertEquals(graph.getNode(x, y).getGraphID(), (long) x % width * width + y);

        for (int id = 0; id < graph.getSize().getTotalSize(); id++)
            Assert.assertEquals(graph.getNode(id).getGraphID(), id);
    }

    @Test
    public void testGetNeighbourNode() {
        Graph graph = GraphGenerator.GenerateExample();

        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(0), Path.Side.RIGHT), graph.getNode(1));
        Assert.assertNull(graph.getNeighbourNode(graph.getNode(0), Path.Side.LEFT));
        Assert.assertNull(graph.getNeighbourNode(graph.getNode(0), Path.Side.TOP));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(0), Path.Side.BOTTOM), graph.getNode(3));

        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(4), Path.Side.RIGHT), graph.getNode(5));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(4), Path.Side.LEFT), graph.getNode(3));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(4), Path.Side.TOP), graph.getNode(1));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(4), Path.Side.BOTTOM), graph.getNode(7));

        Assert.assertNull(graph.getNeighbourNode(graph.getNode(8), Path.Side.RIGHT));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(8), Path.Side.LEFT), graph.getNode(7));
        Assert.assertEquals(graph.getNeighbourNode(graph.getNode(8), Path.Side.TOP), graph.getNode(5));
        Assert.assertNull(graph.getNeighbourNode(graph.getNode(8), Path.Side.BOTTOM));
    }

    @Test
    public void testGetPathSideForConnection() {
        Graph graph = GraphGenerator.GenerateExample();

        try {
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(0), graph.getNode(1)), Path.Side.RIGHT);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(0), graph.getNode(3)), Path.Side.BOTTOM);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(1), graph.getNode(2)), Path.Side.RIGHT);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(1), graph.getNode(4)), Path.Side.BOTTOM);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(4), graph.getNode(1)), Path.Side.TOP);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(4), graph.getNode(3)), Path.Side.LEFT);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(4), graph.getNode(5)), Path.Side.RIGHT);
            Assert.assertEquals(graph.getPathSideBetween(graph.getNode(4), graph.getNode(7)), Path.Side.BOTTOM);
        } catch (Graph.InvalidMeshConnection e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetupPath() {
        Graph graph = new Graph();
        Assert.assertNotNull(graph);

        graph.setSize(new Size(testGrapWidth, testGrapHeight)).setWeight(testGrapMaxPathWeight).build();
        for (int x = 1; x < testGrapWidth-1; x++) {
            for (int y = 1; y < testGrapHeight-1; y++) {
                graph.setupPath(graph.getNode(x, y), Path.Side.TOP, testGrapMaxPathWeight);
                Assert.assertNotNull(graph.getNode(x, y).getPath(Path.Side.TOP));
                graph.setupPath(graph.getNode(x, y), Path.Side.RIGHT, testGrapMaxPathWeight);
                Assert.assertNotNull(graph.getNode(x, y).getPath(Path.Side.RIGHT));
                graph.setupPath(graph.getNode(x, y), Path.Side.BOTTOM, testGrapMaxPathWeight);
                Assert.assertNotNull(graph.getNode(x, y).getPath(Path.Side.BOTTOM));
                graph.setupPath(graph.getNode(x, y), Path.Side.LEFT, testGrapMaxPathWeight);
                Assert.assertNotNull(graph.getNode(x, y).getPath(Path.Side.LEFT));
            }
        }
    }

    @Test
    public void testDeletePath() {
        Graph graph = new Graph();
        Assert.assertNotNull(graph);

        graph.setSize(new Size(testGrapWidth, testGrapHeight)).setWeight(testGrapMaxPathWeight).build();
        for (int x = 1; x < testGrapWidth-1; x++) {
            for (int y = 1; y < testGrapHeight-1; y++) {
                Node tempNode = graph.getNode(x, y);
                graph.setupPath(tempNode, Path.Side.TOP, testGrapMaxPathWeight);
                graph.setupPath(tempNode, Path.Side.RIGHT, testGrapMaxPathWeight);
                graph.setupPath(tempNode, Path.Side.BOTTOM, testGrapMaxPathWeight);
                graph.setupPath(tempNode, Path.Side.LEFT, testGrapMaxPathWeight);
                if(tempNode.getPath(Path.Side.TOP) != null && tempNode.getPath(Path.Side.RIGHT) != null &&
                        tempNode.getPath(Path.Side.BOTTOM) != null && tempNode.getPath(Path.Side.LEFT) != null){
                    graph.deletePath(tempNode, Path.Side.TOP);
                    Assert.assertNull(tempNode.getPath(Path.Side.TOP));
                    graph.deletePath(tempNode, Path.Side.RIGHT);
                    Assert.assertNull(tempNode.getPath(Path.Side.RIGHT));
                    graph.deletePath(tempNode, Path.Side.BOTTOM);
                    Assert.assertNull(tempNode.getPath(Path.Side.BOTTOM));
                    graph.deletePath(tempNode, Path.Side.LEFT);
                    Assert.assertNull(tempNode.getPath(Path.Side.LEFT));
                }
            }
        }
    }

    @Test
    public void testGetNodeXAndY() {
        Graph graph = new Graph();
        Assert.assertNotNull(graph);

        graph.setSize(new Size(testGrapWidth, testGrapHeight)).setWeight(testGrapMaxPathWeight).build();
        for (int x = 0; x < testGrapWidth; x++) {
            for (int y = 0; y < testGrapHeight; y++) {
                Assert.assertEquals(graph.getNodeX(graph.getNode(x, y)), x);
                Assert.assertEquals(graph.getNodeY(graph.getNode(x, y)), y);
            }
        }
    }

    @Test
    public void testIterator() {
        Graph graph = new Graph();
        Assert.assertNotNull(graph);

        graph.setSize(new Size(testGrapWidth, testGrapHeight)).setWeight(testGrapMaxPathWeight).build();
        for(Node n : graph)
            Assert.assertNotNull(n);

    }
}
