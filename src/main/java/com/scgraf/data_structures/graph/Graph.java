package com.scgraf.data_structures.graph;

import com.scgraf.data_structures.tuples.Size;

import java.util.Iterator;

public class Graph implements Iterable<Node> {
    private Node[][] nodes;

    private Size size = new Size(); /* TODO assigndefault values */

    private double maxPathWeight;

    private int graphIDCounter = 0;

    public Graph setWidth(int width) {
        if(size == null) size = new Size();
        size.setWidth(width);
        return this;
    }

    public Graph setHeight(int height) {
        if(size == null) size = new Size();
        size.setHeight(height);
        return this;
    }

    public Graph setSize(Size size) {
        this.size = size;
        return this;
    }

    public Graph setWeight(double weight) {
        this.maxPathWeight = weight;
        return this;
    }

    public void build() {
        nodes = new Node[size.height()][size.width()];

        for (int y = 0; y < size.height(); y++)
            for (int x = 0; x < size.width(); x++)
                nodes[y][x] = new Node(this);
    }

    public double getMaxPathWeight() {
        return maxPathWeight;
    }

    public Node getNeighbourNode(Node node, Path.Side side) {
        int row = getNodeX(node);
        int column = getNodeY(node);

        if (side == Path.Side.LEFT)
            if (column <= 0) return null;
            else column--;

        else if (side == Path.Side.RIGHT)
            if (column >= size.width() - 1) return null;
            else column++;

        else if (side == Path.Side.TOP)
            if (row <= 0) return null;
            else row--;

        else if (side == Path.Side.BOTTOM)
            if (row >= size.height() - 1) return null;
            else row++;


        return getNode(row, column);
    }

    public Path.Side getPathSideBetween(Node startNode, Node endNode) throws InvalidMeshConnection {
        if (startNode.getGraphID() + 1 == endNode.getGraphID()) {
            return Path.Side.RIGHT;
        } else if (startNode.getGraphID() - 1 == endNode.getGraphID()) {
            return Path.Side.LEFT;
        } else if (startNode.getGraphID() + this.getSize().width() == endNode.getGraphID()) {
            return Path.Side.BOTTOM;
        } else if (startNode.getGraphID() - this.getSize().width() == endNode.getGraphID()) {
            return Path.Side.TOP;
        }
        throw new InvalidMeshConnection(startNode, endNode);
    }

    public void setupPath(Node node, Path.Side direction, double pathWeight) {
        setupPath(node, direction, new Path(pathWeight));
    }

    public void setupPath(Node node, Path.Side direction, Path path) {
        try {
            setupPath(node, getNeighbourNode(node, direction), path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupPath(Node node1, Node node2, Path path){
        try {
            Path.Side side = getPathSideBetween(node1, node2);
            node1.setupPath(side, path);
            node2.setupPath(side.getOppositeSide(), path);
        } catch (InvalidMeshConnection e) {
            e.printStackTrace();
        }
    }

    public void deletePath(Node node, Path.Side direction) {
        node.deletePath(direction);
        if (this.getNeighbourNode(node, direction) != null)
            try {
                this.getNeighbourNode(node, direction).deletePath(direction.getOppositeSide());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Node getNode(int row, int column) {
        if(row < 0 || row >= size.width() || column < 0 || column >= size.height())
            return null;
        return nodes[row][column];
    }

    public Node getNode(int graphID) {
        return getNode(getNodeX(graphID), getNodeY(graphID));
    }

    public int getNodeX(Node node) {
        return getNodeX(node.graphID);
    }
    public int getNodeX(int graphID) {
        return graphID / size.width();
    }

    public int getNodeY(Node node) {
        return getNodeY(node.graphID);
    }
    public int getNodeY(int graphID) {
        return graphID % size.width();
    }

    public int getNodesCount() {
        return size.getTotalSize();
    }

    public Size getSize() {
        return size;
    }

    protected int getNextGraphID() {
        return graphIDCounter++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int x = 0; x < this.size.height(); x++) {
            s.append("ROW ").append(x).append(": \n");
            for (int y = 0; y < this.size.width(); y++)
                s.append(nodes[x][y]).append("\n");
        }
        return s.toString();
    }

    @Override
    public Iterator<Node> iterator() {
        return new GraphIterator(this);
    }

    public static class InvalidMeshConnection extends Throwable {
        private String msg = "Invalid connection for a mesh graph!\n";

        public InvalidMeshConnection(Node n1, Node n2) {
            this.msg += "Connection between nodes: " + n1.getGraphID() + " and " + n2.getGraphID() + " couldn' t have been established!\n";
        }

        public void printMessage() {System.err.println(this.msg);}

        @Override
        public String getMessage() {return msg;}
    }

    private static class GraphIterator implements Iterator<Node> {
        private Node[][] t;
        int x, y;
        int lgt;
        int curr;

        public GraphIterator(Graph graph) {
            int n = graph.getNodesCount();
            if (n != 0) {
                this.t = graph.nodes;
                this.x = graph.getSize().width();
                this.y = graph.getSize().height();
            }
            this.lgt = n;
            this.curr = 0;
        }

        @Override
        public boolean hasNext() {
            return curr < lgt;
        }

        @Override
        public Node next() {
            return t[curr / x][curr++ % x];
        }
    }

    public static class NodeNotFoundException extends Throwable {
        @Override
        public String getMessage(){
            return "Could not found the node!";
        }
    }
}
