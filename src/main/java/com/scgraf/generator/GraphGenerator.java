package com.scgraf.generator;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.tuples.Size;

import java.util.Random;

public class GraphGenerator implements IGenerator<Graph> {
    private static final Random randomGenerator = new Random();

    public static Graph GenerateNotDirected(Size size) { /* TODO REFACTOR */
        Graph g = new Graph();
        g.setSize(size).setWeight(1).build();

        for(int y= 0; y<size.height(); y++){
            for(int x = 0; x<size.width(); x++){
                if(x != 0)
                    g.setupPathBothWays(g.getNode(y, x), Path.Side.LEFT, randomGenerator.nextDouble());
                if(x != size.width() - 1)
                    g.setupPathBothWays(g.getNode(y, x), Path.Side.RIGHT, randomGenerator.nextDouble());
                if(y != 0)
                    g.setupPathBothWays(g.getNode(y, x), Path.Side.TOP, randomGenerator.nextDouble());
                if(y != size.height() - 1)
                    g.setupPathBothWays(g.getNode(y,x), Path.Side.BOTTOM, randomGenerator.nextDouble());
            }
        }

        return g;
    }

    public static Graph GenerateDirected(Size size) { /* TODO REFACTOR */
        Graph g = new Graph();
        g.setSize(size).setWeight(1).build();

        for(int y= 0; y<size.height(); y++){
            for(int x = 0; x<size.width(); x++){
                if(x != 0)
                    g.getNode(y, x).setupPath(Path.Side.LEFT, randomGenerator.nextDouble());
                if(x != size.width() - 1)
                    g.getNode(y, x).setupPath(Path.Side.RIGHT, randomGenerator.nextDouble());
                if(y != 0)
                    g.getNode(y, x).setupPath(Path.Side.TOP, randomGenerator.nextDouble());
                if(y != size.height() - 1)
                    g.getNode(y, x).setupPath(Path.Side.BOTTOM, randomGenerator.nextDouble());
            }
        }

        return g;
    }

    public static Graph GenerateExample() {
        Graph g = new Graph();
        g.setWidth(3).setHeight(3).setWeight(3).build();

        g.getNode(0,0).setupPath(Path.Side.RIGHT, 1.4);

        g.getNode(0,1).setupPath(Path.Side.LEFT, 1.4);
        g.getNode(0,1).setupPath(Path.Side.RIGHT, 0.1);
        g.getNode(0,1).setupPath(Path.Side.BOTTOM, 2.3);

        g.getNode(0,2).setupPath(Path.Side.LEFT, 0.1);

        g.getNode(1,0).setupPath(Path.Side.RIGHT, 1.3);
        g.getNode(1,0).setupPath(Path.Side.BOTTOM, 0.1);

        g.getNode(1,1).setupPath(Path.Side.TOP, 2.3);
        g.getNode(1,1).setupPath(Path.Side.RIGHT, 2);
        g.getNode(1,1).setupPath(Path.Side.BOTTOM, 3);
        g.getNode(1,1).setupPath(Path.Side.LEFT, 1.3);

        g.getNode(1,2).setupPath(Path.Side.TOP, 0.1);
        g.getNode(1,2).setupPath(Path.Side.LEFT, 2);

        g.getNode(2,0).setupPath(Path.Side.TOP, 0.1);
        //g.getNode(2,0).setupPath(Path.Side.BOTTOM, 0.2); //TODO Should throw exception

        g.getNode(2,1).setupPath(Path.Side.TOP, 3);
        g.getNode(2,1).setupPath(Path.Side.RIGHT, 1);
        g.getNode(2,1).setupPath(Path.Side.LEFT, 0.2);

        g.getNode(2,2).setupPath(Path.Side.LEFT, 1);
        return g;
    }

    public static Graph GraphForDivision(){
        Graph g = new Graph();
        g.setWidth(4).setHeight(4).build();

        g.setupPathBothWays(g.getNode(0,0), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(0,0), Path.Side.BOTTOM, 0.001);

        g.setupPathBothWays(g.getNode(0,1), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(0,1), Path.Side.BOTTOM, 1);

        g.setupPathBothWays(g.getNode(0,2), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(0,2), Path.Side.BOTTOM, 1);

        g.setupPathBothWays(g.getNode(0,3), Path.Side.BOTTOM, 0.001);

        //ROW 2

        g.setupPathBothWays(g.getNode(1,0), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(1,0), Path.Side.BOTTOM, 0.001);

        g.setupPathBothWays(g.getNode(1,1), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(1,1), Path.Side.BOTTOM, 0.01);

        g.setupPathBothWays(g.getNode(1,2), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(1,2), Path.Side.BOTTOM, 1);

        g.setupPathBothWays(g.getNode(1,3), Path.Side.BOTTOM, 0.001);

        //ROW 3

        g.setupPathBothWays(g.getNode(2,0), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(2,0), Path.Side.BOTTOM, 0.001);

        g.setupPathBothWays(g.getNode(2,1), Path.Side.RIGHT, 0.01);
        g.setupPathBothWays(g.getNode(2,1), Path.Side.BOTTOM, 1);

        g.setupPathBothWays(g.getNode(2,2), Path.Side.RIGHT, 1);
        g.setupPathBothWays(g.getNode(2,2), Path.Side.BOTTOM, 0.01);

        g.setupPathBothWays(g.getNode(2,3), Path.Side.BOTTOM, 0.001);

        //ROW 4

        g.setupPathBothWays(g.getNode(3,0), Path.Side.RIGHT, 0.001);

        g.setupPathBothWays(g.getNode(3,1), Path.Side.RIGHT, 0.001);

        g.setupPathBothWays(g.getNode(3,2), Path.Side.RIGHT, 0.001);
        return g;
    }
}
