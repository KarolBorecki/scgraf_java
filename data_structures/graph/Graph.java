package data_structures.graph;

import java.util.Arrays;

public class Graph {
    private Node[][] nodes;
    private int width = 10;
    private int height = 10;

    private static int nodeIdCounter;

    public Graph setWidth(int width){
        this.width = width;
        return this;
    }

    public Graph setHeight(int height){
        this.height = height;
        return this;
    }

    public Graph setNodes(Node[][] nodes){
        this.nodes = nodes;
        return this;
    }

    public void build(){
        if(nodes == null)
            nodes = new Node[width][height];
    }

    public static int getNextNodeId() {
        return nodeIdCounter++;
    }

    @Override
    public String toString(){
        return "Graph:" +
                "\n   Width: " + width +
                "\n   Height: " + height +
                "\n   Nodes: " + Arrays.deepToString(nodes);
    }

}
