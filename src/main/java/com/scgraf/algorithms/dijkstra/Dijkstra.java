package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.PriorityQueue;

public class Dijkstra implements Algorithm {

    private final static int STARTING_SIZE = 4;
    private final Graph graph;

    private DijkstraData[] dijkstraTable;

    private PriorityQueue<DijkstraData> queToVisit;
    private boolean isSolved = false;

    public Dijkstra(Graph g) {
        this.graph = g;
    }

    private void initializeDijkstraTable(Node startNode) {
        this.dijkstraTable = new DijkstraData[graph.getNodesCount()];
        this.queToVisit = new PriorityQueue<>(graph.getNextGraphID());
        for (int i = 0; i < graph.getSize().height(); i++)
            for (int j = 0; j < graph.getSize().width(); j++) {
                int indexInGraph = i * graph.getSize().width() + j;
                dijkstraTable[indexInGraph] = new DijkstraData(graph.getNode(i, j));
                queToVisit.push(dijkstraTable[indexInGraph]);
            }

        this.dijkstraTable[startNode.getGraphID()].length = 0;
        this.dijkstraTable[startNode.getGraphID()].previousNode = startNode;
    }

    @Override
    public void Solve() {

    }

    public void Solve(Node startNode, Node finishNode) {
        initializeDijkstraTable(startNode);
        while (!queToVisit.IsEmpty()) {
            Node currentNode = queToVisit.pop().getCurrentNode();
            for (Path.Side side : Path.Side.values()) {
                if (currentNode.isConnected(side)) {

                    double valToCheck = currentNode.getConnectionWeight(side) + dijkstraTable[currentNode.getGraphID()].length;
                    Node Neighbour = graph.getNeighbourNode(currentNode, side);

                    if (Neighbour.getGraphID() >= 0 && Neighbour.getGraphID() < graph.getNodesCount() && valToCheck < dijkstraTable[Neighbour.getGraphID()].length) {
                        dijkstraTable[Neighbour.getGraphID()].length = valToCheck;
                        dijkstraTable[Neighbour.getGraphID()].previousNode = currentNode;
                    }
                }
            }
        }
        isSolved = true;
    }

    public Node[] getShortestPath(Node startNode, Node finishNode) {
        if(!isSolved)//TODO Should throw an error
            return null;
        Node[] t = new Node[STARTING_SIZE];

        Node helpNode = finishNode;

        int i = 0;
        while (!helpNode.equals(startNode)) {
            if (i >= t.length)
                t = doubledT(t);
            t[i] = helpNode;
            helpNode = dijkstraTable[helpNode.getGraphID()].previousNode;
            i++;
        }
        if (i >= t.length)
            t = doubledT(t);
        t[i] = helpNode;

        t = removeNull(t);

        return t;
    }

    public double getShortestPathLength(Node startNode, Node finishNode) {
        if(!isSolved)//TODO Should throw an error
            return -1;
        double shortestPath = 0;
        if (finishNode == startNode)
            return 0;
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            shortestPath += dijkstraTable[i].length;
        return shortestPath;
    }

    public String toStringDijkstraTable() {
        StringBuilder s = new StringBuilder();
        s.append("Node index\tPath Length\tPrev Node\n");
        for (int i = 0; i < graph.getNodesCount(); i++)
            s.append("\"Node ").append(i).append("\"").append(dijkstraTable[i]).append("\n");

        return s.toString();
    }

    public String getShortestPathString(Node startNode, Node finishNode) {
        if(!isSolved)//TODO Should throw an error
            return null;
        StringBuilder path = new StringBuilder();
        if (finishNode == startNode)
            return path.toString();
        path.append(finishNode.getGraphID());
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            path.append(" -> ").append(dijkstraTable[i].previousNode.getGraphID());
        path.append(" -> ").append(startNode.getGraphID());
        return path.toString();
    }

    public String getDijkstraResult(Node startNode, Node finishNode) {
        if(!isSolved)//TODO Should throw an error
            return null;
        String s = "";
        s += ("-----Dijkstra result-----\n");
        s += "\tShortest path from: " + startNode.getGraphID() + " to " + finishNode.getGraphID() + " = " + this.getShortestPathLength(startNode, finishNode) + "\n";
        s += "\tThe following path:\n\t" + this.getShortestPathString(startNode, finishNode) + "\n";
        return s;
    }

    @Override
    public String toString() {
        if (!isSolved) {
            //TODO Should Throw an error
            System.err.println("Table havent been solved yet!");
            return null;
        }

        StringBuilder s = new StringBuilder();
        s.append("-----Dijkstra table-----\n");
        s.append("NODE\t\t\tS. PATH\t\t\tPREV NODE\n");
        for (int i = 0; i < graph.getNodesCount(); i++)
            s.append(dijkstraTable[i].getCurrentNode().getGraphID()).append("\t\t\t").append(dijkstraTable[i].length).append("\t\t\t").append((dijkstraTable[i].previousNode == null ? "-" : dijkstraTable[i].previousNode.getGraphID())).append("\n");
        return s.toString();
    }

    private Node [] doubledT(Node [] t){
        Node[] oldt = new Node[t.length];
        System.arraycopy(t, 0, oldt, 0, t.length);
        t = new Node[t.length * 2];
        System.arraycopy(oldt, 0, t, 0, oldt.length);
        return t;
    }

    private Node [] removeNull(Node [] t){
        int ammountOfNotNull = 0;
        for(; ammountOfNotNull < t.length && t[ammountOfNotNull] != null; ammountOfNotNull++)
            ;
        Node [] tWithoutNulls = new Node [ammountOfNotNull];
        System.arraycopy(t, 0, tWithoutNulls, 0, ammountOfNotNull);
        return tWithoutNulls;
    }

    protected static class DijkstraData implements Comparable<DijkstraData> {
        public double length = Double.MAX_VALUE;
        public Node previousNode = null;
        public Node currentNode;

        public DijkstraData(Node node) {
            this.currentNode = node;
        }

        public Node getCurrentNode() {
            return currentNode;
        }

        public Node getPreviousNode() {
            return previousNode;
        }

        @Override
        public int compareTo(DijkstraData o) {
            return this.length < o.length ? 1 : -1;
        }

        @Override
        public String toString() {
            return "\t" + length + "\t" + (previousNode == null ? "-" : previousNode.getGraphID());
        }
    }
}
