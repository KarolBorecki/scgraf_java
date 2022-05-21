package data_structures.graph;

import data_structures.tuples.Size;

import java.util.Iterator;

public class Graph implements Iterable<Node>{
    private Node[][] nodes;

    private Size size = new Size(); /* TODO assigndefault values */

    private double maxConnectionWeight;

    private int graphIDCounter = 0;

    public Graph setWidth(int width){
        size.setWidth(width);
        return this;
    }

    public Graph setHeight(int height){
        size.setHeight(height);
        return this;
    }

    public Graph setSize(Size size){
        this.size = size;
        return this;
    }

    public void build(){
        nodes = new Node[size.height()][size.width()];

        for(int y = 0; y<size.height(); y++)
            for(int x = 0; x<size.width(); x++)
                nodes[y][x] = new Node(this);
    }

    public double getMaxConnectionWeight(){
        return maxConnectionWeight;
    }
/*
    public Node getNeighbourNode(Node node, Path.Side side){
        return nodes[node.graphID / size.height() + (side == Path.Side.LEFT ? -1 : side == Path.Side.RIGHT ? 1 : 0)][node.graphID % size.height() + (side == Path.Side.TOP ? -size.width() : side == Path.Side.BOTTOM ? size.width() : 0)];
    }
*/
    public Node getNeighbourNode(Node node, Path.Side side){
        int row = node.graphID / size.height();
        int column = node.graphID % size.height();

        if(side == Path.Side.LEFT)
            if(column <= 0) return null;
            else column--;

        else if(side == Path.Side.RIGHT)
            if(column >= size.width()) return null;
            else column++;

        else if(side == Path.Side.TOP)
            if(row <= 0) return null;
            else row--;

        else if(side == Path.Side.BOTTOM)
            if(row >= size.height()) return null;
            else row++;


        return getNode(row, column);
    }

    /* TODO DELTE THIS - RETURNS MAGIC NUMBER NOT  ASSOSCIATED WITH GRAPH!!! */
    public int getIdOfConnectedVertex(Node nodeFrom, Path.Side side){
        int currentId = nodeFrom.graphID;

        if(side == Path.Side.LEFT)
            return currentId - 1;
        else if(side == Path.Side.RIGHT)
            return currentId + 1;
        else if(side == Path.Side.TOP)
            return currentId - size.width();
        else if(side == Path.Side.BOTTOM)
            return currentId + size.width();

        //TODO Should be throwing error if connection is invalid(? unless we handle it already in connections ?)
        return -1;
    }

    public Node getNode(int row, int column){
        return nodes[row][column];
    }

    public Node getNode(int id){ /* TODO DELTE! */
        return nodes[id / this.getSize().width()][id % this.getSize().width()];
    }

    public int getNodesCount(){
        return size.getTotalSize();
    }

    public Size getSize(){
        return size;
    }

    public int getNextGraphID(){
        return graphIDCounter++;
    }

    public void setMaxConnectionWeight(double newMaxConnectionWeight){
        //TODO maybe we should update this value depended by analysing the graph connections?
        maxConnectionWeight = newMaxConnectionWeight;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int x = 0; x < this.size.height(); x++) {
            s.append("ROW ").append(x).append(": \n");
            for (int y = 0; y < this.size.width(); y++)
                s.append(nodes[x][y]).append("\n");
        }
        return s.toString();
    }

    @Override
    public Iterator<Node> iterator() {
        return new GraphIterator(this.nodes, this.getSize().width(), this.getSize().height());
    }

    private static class GraphIterator implements Iterator<Node> {

        private Node [][] t;
        int x, y;
        int lgt;
        int curr;

        public GraphIterator(Node [][] t, int x, int y){
            int n = x * y;
            if(n != 0) {
                this.t = new Node[y][x];
                this.x = x;
                this.y = y;
                for(int i= 0; i < y; i++){
                    System.arraycopy(t[i], 0, this.t[i], 0, x);
                }
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

}
