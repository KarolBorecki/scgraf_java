package com.scgraf.algorithms;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.PriorityQueue;
import com.scgraf.utils.Utils;

public class Dijkstra {

    private final static int STARTING_SIZE = 4;
    private final Graph graph;

    private DijkstraData[] dijkstraTable;

    private PriorityQueue<DijkstraData> queToVisit;

    private boolean isSolved = false;
    private Node solvedForStartingNode = null;

    public Dijkstra(Graph g) {
        this.graph = g;
    }

    public void Solve(Node startNode) {
        initializeDijkstraTable(startNode);
        while (!queToVisit.isEmpty()) {
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
        this.solvedForStartingNode = startNode;
    }

    public Node[] getShortestPath(Node finishNode) throws DijkstraNotSolvedException, DijkstraCannotFindPathException {
        if (!isSolved)
            throw new DijkstraNotSolvedException();

        if (this.solvedForStartingNode == null || finishNode == null)
            return null;

        Node[] t = new Node[STARTING_SIZE];

        Node helpNode = finishNode;

        int i = 0;
        while (!helpNode.equals(this.solvedForStartingNode)) {
            if (i >= t.length)
                t = Utils.resizeArray(t, 2);
            t[i] = helpNode;
            if (dijkstraTable[helpNode.getGraphID()].previousNode == null)
                throw new DijkstraCannotFindPathException();
            helpNode = dijkstraTable[helpNode.getGraphID()].previousNode;
            i++;
        }
        t = Utils.resizeArrayTo(t, i + 1);
        t[i] = helpNode;

        return t;
    }

    public double getShortestPathLength(Node finishNode) {
        if (!isSolved)//TODO Should throw an error
            return -1;
        double shortestPath = 0;
        if (finishNode == this.solvedForStartingNode)
            return 0;
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != this.solvedForStartingNode; i = dijkstraTable[i].previousNode.getGraphID())
            shortestPath += dijkstraTable[i].length;
        return shortestPath;
    }

    public String getShortestPathString(Node finishNode) {
        if (!isSolved)//TODO Should throw an error
            return null;
        StringBuilder path = new StringBuilder();
        if (finishNode == this.solvedForStartingNode)
            return path.toString();
        path.append(finishNode.getGraphID());
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != this.solvedForStartingNode; i = dijkstraTable[i].previousNode.getGraphID())
            path.append(" -> ").append(dijkstraTable[i].previousNode.getGraphID());
        path.append(" -> ").append(this.solvedForStartingNode.getGraphID());
        return path.toString();
    }

    public String getDijkstraResult(Node finishNode) throws DijkstraNotSolvedException {
        if (!isSolved)//TODO Should throw an error
            throw new DijkstraNotSolvedException();
        String s = "";
        s += ("-----Dijkstra result-----\n");
        s += "\tShortest path from: " + this.solvedForStartingNode.getGraphID() + " to " + finishNode.getGraphID() + " = " + this.getShortestPathLength(finishNode) + "\n";
        s += "\tThe following path:\n\t" + this.getShortestPathString(finishNode) + "\n";
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

    public Node getSolvedForStartingNode() {
        return this.solvedForStartingNode;
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

    //TODO DELETE AND USE LOGGER
    public class DijkstraNotSolvedException extends Throwable {

    }

    public class DijkstraCannotFindPathException extends Throwable {

    }
}