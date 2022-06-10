package com.scgraf.data_structures.graph;

import com.scgraf.data_structures.tuples.Size;

import java.util.Iterator;

public class Graph implements Iterable<Node> {
    private Node[][] nodes;

    private Size size = new Size(); /* TODO assigndefault values */

    private double maxConnectionWeight;

    private int graphIDCounter = 0;

    public Graph setWidth(int width) {
        size.setWidth(width);
        return this;
    }

    public Graph setHeight(int height) {
        size.setHeight(height);
        return this;
    }

    public Graph setSize(Size size) {
        this.size = size;
        return this;
    }

    public Graph setWeight(double weight) {
        this.maxConnectionWeight = weight;
        return this;
    }

    public void build() {
        nodes = new Node[size.height()][size.width()];

        for (int y = 0; y < size.height(); y++)
            for (int x = 0; x < size.width(); x++)
                nodes[y][x] = new Node(this);
    }

    public double getMaxConnectionWeight() {
        return maxConnectionWeight;
    }

    /*
        public Node getNeighbourNode(Node node, Path.Side side){
            return nodes[node.graphID / size.height() + (side == Path.Side.LEFT ? -1 : side == Path.Side.RIGHT ? 1 : 0)][node.graphID % size.height() + (side == Path.Side.TOP ? -size.width() : side == Path.Side.BOTTOM ? size.width() : 0)];
        }
    */
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

    public Path.Side getPathSideForConnection(Node startNode, Node endNode) throws InvalidMeshConnection {
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

    public void setupPathBothWays(Node node, Path.Side side, Path path) {
        node.setupPath(side, path);
        try {
            this.getNeighbourNode(node, side).setupPath(side.getOppositeSide(), path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupPathBothWays(Node node, Path.Side side, double pathWeight) {
        node.setupPath(side, pathWeight);
        try {
            this.getNeighbourNode(node, side).setupPath(side.getOppositeSide(), pathWeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePathBothWays(Node node, Path.Side side) {
        node.deletePath(side);
        if (this.getNeighbourNode(node, side) != null)
            try {
                this.getNeighbourNode(node, side).deletePath(side.getOppositeSide());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Node getNode(int row, int column) {
        return nodes[row][column];
    }

    //TODO change - we can calculate row and column for this
    public Node getNode(int graphID) {
        for (Node node : this)
            if (node.getGraphID() == graphID) return node;
        return null;
    }

    public int getNodeX(Node node) {
        return node.getGraphID() / size.width();
    }

    public int getNodeY(Node node) {
        return node.getGraphID() % size.width();
    }

    public int getNodesCount() {
        return size.getTotalSize();
    }

    public Size getSize() {
        return size;
    }

    public int getNextGraphID() {
        return graphIDCounter++;
    }

    public void setMaxConnectionWeight(double newMaxConnectionWeight) {
        //TODO maybe we should update this value depended by analysing the graph connections?
        maxConnectionWeight = newMaxConnectionWeight;
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
            int n = graph.getSize().width() * graph.getSize().height();
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
