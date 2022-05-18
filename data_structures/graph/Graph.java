package data_structures.graph;

import java.util.Arrays;

import data_structures.tuples.Size;

public class Graph {
    private Node[][] nodes;

    private final Size size = new Size();

    private double maxConnectionWeight;

    public Graph setWidth(int width){
        size.setWidth(width);
        return this;
    }

    public Graph setHeight(int height){
        size.setHeight(height);
        return this;
    }

    public void build(){
        nodes = new Node[size.width()][size.height()];

        for(int x = 0; x<size.width(); x++)
            for(int y = 0; y<size.height(); y++)
                nodes[x][y] = new Node();
    }

    public double getMaxConnectionWeight(){
        return maxConnectionWeight;
    }

    public Node getNode(int x, int y){
        return nodes[x][y];
    }

    public Size getSize(){
        return size;
    }

    public void setMaxConnectionWeight(double newMaxConnectionWeight){
        //TODO maybe we should update this value depended by analysing the graph connections?
        maxConnectionWeight = newMaxConnectionWeight;
    }

    @Override
    public String toString(){
        return "Graph:" +
                "\n   Width: " + size.width() +
                "\n   Height: " + size.height() +
                "\n   Nodes: " + Arrays.deepToString(nodes);
    }

}
